using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class AssignStatement : IStatement
    {
        private String variable;
        private IExpression expression;

        public AssignStatement(String var, IExpression exp)
        {
            this.variable = var;
            this.expression = exp;
        }

        public ProgramState Execute(ProgramState state)
        {
            try
            {
                int result = this.expression.Eval(state.SymTable);
                state.SymTable.Put(variable, result);
                return state;
            }
            catch (DivideByZeroException)
            {
                throw new DivideByZeroException("DIVISION BY ZERO. Cannot divide by 0.");
            }
            catch(UndefinedVariableException)
            {
                throw new UndefinedVariableException(this.variable);
            }
        }

        public override string ToString()
        {
            return this.variable + " = " + this.expression.ToString();
        }
    }
}
