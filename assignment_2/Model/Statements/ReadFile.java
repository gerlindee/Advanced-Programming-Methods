package Model.Statements;

import ADTs.IDictionary;
import ADTs.IStack;
import ADTs.Tuple;
import Exceptions.DivisionByZeroException;
import Exceptions.MissingBufferReaderException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Model.Expressions.IExpression;
import Model.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private IExpression exp_file;
    private String var_name;

    public ReadFile(IExpression ex, String name) {
        this.exp_file = ex;
        this.var_name = name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MissingBufferReaderException, IOException {
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<Integer, Tuple<String, BufferedReader>> file = state.getFileTable();
        IDictionary<String, Integer> sym = state.getSymTable();
        try {
            int value = this.exp_file.eval(sym);
            BufferedReader reader = file.get(value).getSecond();
            if(reader == null)
                throw new MissingBufferReaderException("Nu such file!");
            else {
                String line = reader.readLine();
                int new_value = 0;
                if(line != null)
                    new_value = Integer.parseInt(line);
                sym.put(this.var_name, new_value);
            }
        } catch (UndefinedVariableException ex) {
            System.out.println(ex.getMessage());
        } catch (DivisionByZeroException ex1) {
            System.out.println(ex1.getMessage());
        } catch (UndefinedOperationException ex2) {
            System.out.println(ex2.getMessage());
        }
        return state;
    }
}
