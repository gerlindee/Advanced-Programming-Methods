package Model.Statements;

import Exceptions.*;
import Model.ProgramState;

import java.io.IOException;

public interface IStatement {
    String toString();
    ProgramState execute(ProgramState state) throws HeapReadingException, IOException, MissingBufferReaderException, UndefinedVariableException, DivisionByZeroException, UndefinedOperationException, ExistingFileException;
}
