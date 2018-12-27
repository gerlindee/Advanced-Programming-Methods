using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class ArithmeticExpression : IExpression
    {
        private IExpression exp1;
        private IExpression exp2;
        private String op;
        
        public ArithmeticExpression(IExpression left, IExpression right, String oper)
        {
            this.exp1 = left;
            this.exp2 = right;
            this.op = oper;
        }

        public override int Eval(IDictionary<string, int> table)
        {
            int result = 0;
            switch(this.op)
            {
                case "+": result = this.exp1.Eval(table) + this.exp2.Eval(table); break;
                case "-": result = this.exp1.Eval(table) - this.exp2.Eval(table); break;
                case "*": result = this.exp1.Eval(table) * this.exp2.Eval(table); break;
                case "/":
                    {
                        if (this.exp2.Eval(table) == 0)
                            throw new DivideByZeroException("DIVISION BY ZERO. Attempted to divide by 0.");
                        result = this.exp1.Eval(table) / this.exp2.Eval(table);
                    } break;
                default: throw new UndefinedOperationException("UNDEFINED OPERATION. The operator being used is not valid.");
            }
            return result;
        }

        public override string ToString()
        {
            return this.exp1.ToString() + " " + this.op + " " + this.exp2.ToString();
        }
    }
}
