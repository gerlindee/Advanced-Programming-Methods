package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;

public class CloseRFile implements IStatement {
    private IExpression exp_file_id;

    public CloseRFile(IExpression id) {
        this.exp_file_id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        return state;
    }
}
