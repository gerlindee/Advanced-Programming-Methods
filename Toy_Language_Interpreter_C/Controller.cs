using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class Controller
    {
        private IRepo repository;

        public Controller(IRepo repo)
        {
            this.repository = repo;
        }

        public bool IsEmpty()
        {
            return this.repository.IsEmpty();
        }

        public void AddProgram(ProgramState prg)
        {
            this.repository.Add(prg);
        }

        public ProgramState OneStep(ProgramState state)
        {
            try
            {
                IStatement currentStmt = state.ExeStack.Pop();
                state = currentStmt.Execute(state);
            }
            catch(EmptyStackException ex2)
            {
                Console.WriteLine(ex2.ToString());
            }
            catch(DivideByZeroException ex3)
            {
                Console.WriteLine(ex3.Message);
            }
            catch(UndefinedOperationException ex4)
            {
                Console.WriteLine(ex4.ToString());
            }
            catch(UndefinedVariableException ex5)
            {
                Console.WriteLine(ex5.ToString());
            }
            catch(MissingTextReaderException ex6)
            {
                Console.WriteLine(ex6.ToString());
            }
            catch(EndOfFileException ex7)
            {
                Console.WriteLine(ex7.ToString());
            }
            return state;
        }

        public void AllStep()
        {
            ProgramState state = this.repository.GetCrtPrg();
            while(!state.ExeStack.IsEmpty())
            {
                this.OneStep(state);
                Console.Write(state.ToString());
                this.repository.LogPrgStateExec(state);
            }
            Console.Write("\n");
        }
    }
}
