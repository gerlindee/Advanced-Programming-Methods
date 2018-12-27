using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class ConstantExpression : IExpression
    {
        private int number;

        public ConstantExpression(int num)
        {
            this.number = num;
        }

        public override int Eval(IDictionary<string, int> table)
        {
            return this.number;
        }

        public override string ToString()
        {
            return this.number.ToString();
        }
    }
}
