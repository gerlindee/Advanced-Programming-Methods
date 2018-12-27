using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class CompStatement : IStatement
    {
        private IStatement first;
        private IStatement second;

        public CompStatement(IStatement one, IStatement two)
        {
            this.first = one;
            this.second = two;
        }

        public ProgramState Execute(ProgramState state)
        {
            state.ExeStack.Push(this.second);
            state.ExeStack.Push(this.first);
            return state;
        }

        public override string ToString()
        {
            return "(" + this.first.ToString() + ";" + this.second.ToString() + ")";
        }
    }
}
