using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class IfStatement : IStatement
    {
        private IExpression exe;
        private IStatement stmt1;
        private IStatement stmt2;

        public IfStatement(IExpression ex, IStatement thenS, IStatement elseS)
        {
            this.exe = ex;
            this.stmt1 = thenS;
            this.stmt2 = elseS;
        }

        public ProgramState Execute(ProgramState state)
        {
            try
            {
                if(this.exe.Eval(state.SymTable) == 0)
                {
                    state.ExeStack.Push(this.stmt2);
                }
                else
                {
                    state.ExeStack.Push(this.stmt1);
                }
                return state;
            }
            catch (DivideByZeroException)
            {
                throw new DivideByZeroException("DIVISION BY ZERO. Cannot divide by 0.");
            }
            catch (UndefinedVariableException)
            {
                throw new UndefinedVariableException();
            }
            catch (UndefinedOperationException)
            {
                throw new UndefinedOperationException();
            }
        }

        public override string ToString()
        {
            return "IF " + this.exe.ToString() + " THEN " + this.stmt1.ToString() + " ELSE " + this.stmt2.ToString();
        }
    }
}
