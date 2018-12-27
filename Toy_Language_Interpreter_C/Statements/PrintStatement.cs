using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class PrintStatement : IStatement
    {
        private IExpression expression;

        public PrintStatement(IExpression exp)
        {
            this.expression = exp;
        }

        public ProgramState Execute(ProgramState state)
        {
            try
            {
                int result = this.expression.Eval(state.SymTable);
                state.OutTable.AddFirst(result);
                return state;
            }
            catch(DivideByZeroException)
            {
                throw new DivideByZeroException("DIVISION BY ZERO. Cannot divide by 0.");
            }
            catch(UndefinedVariableException)
            {
                throw new UndefinedVariableException();
            }
            catch(UndefinedOperationException)
            {
                throw new UndefinedOperationException();
            }
        }

        public override string ToString()
        {
            return "Print(" + this.expression.ToString() + ")";
        }
    }
}
