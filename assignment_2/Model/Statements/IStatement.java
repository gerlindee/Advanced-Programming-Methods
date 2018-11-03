package Model.Statements;

import Model.ProgramState;

public interface IStatement {
    String toString();
    ProgramState execute(ProgramState state);
}
