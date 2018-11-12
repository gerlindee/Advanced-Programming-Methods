package Model.Statements;

import Exceptions.ExistingFileException;
import Exceptions.MissingBufferReaderException;
import Model.ProgramState;

import java.io.IOException;

public interface IStatement {
    String toString();
    ProgramState execute(ProgramState state) throws ExistingFileException, IOException, MissingBufferReaderException;
}
