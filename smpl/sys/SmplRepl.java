package smpl.sys;

import cs34q.gfx.GraphingFrame;
import cs34q.gfx.GraphingFrame.CmdListener;
import cs34q.gfx.GraphingPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import smpl.semantics.SmplEval;
import smpl.values.SmplObj;
import smpl.semantics.Visitor;
import smpl.syntax.ASTNode;
import smpl.syntax.SmplLexer;
import smpl.syntax.SmplParser;
import smpl.syntax.SmplProgram;

public class SmplRepl {

    public static final String PROMPT = "> ";

    private static final String MESSAGE = "Type your input at the prompt."
            + "  Terminate with a '.' on a line by itself.\n"
            + "Quit by entering a '.' as the only line or by sending EOF to input.";

    public static void main(String args[]) {
        SmplEval interp = new SmplEval();
        interp.init();    // setup global environment of interperetor

        // need to decouple interpreter computation from GUI thread
        //frame.addCmdListener(mkEvaluatingListener(frame, interp));
        boolean hasGraphics = false;
        boolean useRepl = false;
        ArrayList<String> inputFiles = new ArrayList<>();

        for (String fname : args) {
            if (fname.equals("-gfx")) {
                hasGraphics = true;
            } else if (fname.equals("-")) {
                useRepl = true;
            } else {
                inputFiles.add(fname);
            }
        }
        if (inputFiles.isEmpty()) {
            useRepl = true;
        }

        for (String fname : inputFiles) {
            Reader fr;
            try {
                fr = new FileReader(fname);
                System.out.println("Processing " + fname + "...");
                repl(interp, fr);
                fr.close();
            } catch (FileNotFoundException fnfe) {
                System.err.println(fnfe.getMessage());
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }

        if (hasGraphics) {
            JFrame frame = new JFrame("SMPL View");
            GraphingPanel view = setupDisplay(frame);
            interp.setView(view);
            if (useRepl) {
                System.out.println(MESSAGE);
                repl(interp, new InputStreamReader(System.in));
            }
            frame.dispose();
        } else if (useRepl) {
                System.out.println(MESSAGE);
                repl(interp, new InputStreamReader(System.in));
        }
    }

    /**
     * Configure a graphical display for Smpl programs. If the frame passed is
     * an instance of a GrpahingFrame, the drawing widget will be retrieved and
     * returned. Otherwise, a fresh drawing widget will be created and hosted
     * within the frame passed.
     *
     * @param frame The frame within which to host the drawing widget.
     * @return The newly created drawing widget.
     */
    public static GraphingPanel setupDisplay(JFrame frame) {
        GraphingPanel smplView;
        if (frame instanceof GraphingFrame) {
            GraphingFrame gframe = (GraphingFrame) frame;
            gframe.setSize(800, 800);
            Style outStyle = gframe.addOutputStyle("output", null);
            StyleConstants.setBold(outStyle, true);
            Style errStyle = gframe.addOutputStyle("error", null);
            StyleConstants.setBold(errStyle, true);
            StyleConstants.setForeground(errStyle, Color.RED);
            smplView = gframe.getGraphArea();
        } else {
            smplView = new GraphingPanel();
            frame.setSize(600, 600);
            frame.setLayout(new GridLayout(1, 1));
            frame.add(smplView);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        smplView.setSize(500, 500);
//        smplView.setPenColour(Color.BLUE);
//        smplView.drawLine(-100, -100, 100, 100);

        smplView.setPenColour(Color.BLACK);
        smplView.scale(10, 10);
        smplView.showXAxis(true);
        smplView.showYAxis(true);

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
                //new GraphingFrame().setVisible(true);
            }
        });

        return smplView;
    }

    private static CmdListener mkEvaluatingListener(GraphingFrame gf, SmplEval interp) {
        final Style defaultStyle = gf.getOutputStyle("default");
        final Style outStyle = gf.getOutputStyle("output");
        final Style errStyle = gf.getOutputStyle("error");
        return new CmdListener() {
            @Override
            public void cmdReceived(String commands, GraphingFrame gf) {
                StringReader cmdReader = new StringReader(commands);
                try {
                    SmplObj result = parseVisit(cmdReader, interp, interp.getGlobalEnv());
                    gf.display("Result: ", outStyle);
                    gf.display(result.toString(), defaultStyle);
                } catch (TokenException te) {
                    gf.display("Lexing Error: ", errStyle);
                    gf.display(te.getMessage(), defaultStyle);
                } catch (SmplParseException sae) {
                    gf.display("Parsing Error: ", errStyle);
                    gf.display(sae.getMessage(), defaultStyle);
                } catch (SmplException sae) {
                    gf.display("Runtime Error:", errStyle);
                    gf.display(sae.getMessage(), defaultStyle);
                } catch (Exception e) {
                    gf.display("Unexpected Error: ", errStyle);
                    gf.display(e.getMessage(), defaultStyle);
                    e.printStackTrace(System.err);
                }
                gf.display("\n\n", defaultStyle);
            }
        };
    }

    public static void repl(SmplEval interp, Reader in) {
        LineNumberReader scanner = null;
        SmplObj result;
        try {
            StringBuilder input = new StringBuilder(256);
            scanner = new LineNumberReader(in);
            while (true) {
                try {
                    System.out.print(PROMPT);
                    String line = scanner.readLine();
                    while (line != null && !line.equals(".")) {
                        input.append(line);
                        line = scanner.readLine();
                    }
                    if (input.toString().equals("")) {
                        break;
                    } else {
                        System.out.println("Input: " + input);
                        result = parseEvalShow(interp, new StringReader(input.toString()));
                        input.delete(0, input.length());
                    }
                    if (line == null) {
                        break;
                    }
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        } finally {
            try {
                if (scanner != null) {
                    scanner.close();
                }
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
    }

    public static <S, T> T parseVisit(Reader input, Visitor<S, T> visitor, S state)
            throws SmplException {
        SmplParser parser;
        ASTNode program;
        T result;
        try {
            // construct the parser for the given input
            parser = new SmplParser(new SmplLexer(input));
            // parse the input and construct the Abstract Syntax Tree (AST)
            program = (SmplProgram) parser.parse().value;
        } catch (TokenException te) {
            throw (te);
        } catch (Exception e) {
            throw new SmplParseException(e.getMessage(), e);
        }
        // visit the tree to obtain a result
        result = program.visit(visitor, state);
        return result;
    }

    public static SmplObj parseEvalShow(SmplEval interp, Reader reader) {
//	SmplParser parser;
//        ASTNode program;
//        Environment<SmplObject> env = interp.getEnv();
        SmplObj result = null;
        try {
            // Evaluating input is just visiting with the interpreter
            result = parseVisit(reader, interp, interp.getGlobalEnv());
            // Display the result
            System.out.println("Result: " + result);
//	    //InputStreamReader reader = new InputStreamReader(is);
//	    parser = new SmplParser(new SmplLexer(reader));
//	    // special case: normally we would not evaluate while parsing
//	    program = (SmplProgram) parser.parse().value;
//            result = program.visit(interp, env);
//	    // instead of having an integer here, we would typically have a
//	    // tree to represent the program parsed (an AST), which would
//	    // later be walked in order to produce a value.
        } catch (TokenException te) {
            System.err.println("Lexing Error: " + te.getMessage());
        } catch(SmplParseException spe) {
            System.err.println("Parsing Error: " + spe.getMessage());
        } catch (SmplException e) {
            System.err.println("Runtime Error: " + e.getMessage());
            //e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unanticipated Error: " + e.getMessage());
            e.printStackTrace();
        }

        return result;

    }

}
