using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class VarExpression : IExpression
    {
        private String variable;
        
        public VarExpression(String var)
        {
            this.variable = var;
        }

        public override int Eval(IDictionary<string, int> table)
        {
            try
            {
                return table.Get(this.variable);
            }
            catch(UndefinedOperationException)
            {
                throw new UndefinedVariableException(this.variable);
            }
        }

        public override string ToString()
        {
            return this.variable;
        }
    }
}
