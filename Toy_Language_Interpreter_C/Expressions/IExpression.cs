using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    public abstract class IExpression
    {
        abstract public int Eval(IDictionary<String, int> table);
        abstract public override String ToString();
    }
}
