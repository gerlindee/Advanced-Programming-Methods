using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class EmptyStackException : Exception
    {
        private String msg;

        public EmptyStackException() { this.msg = "EMPTY STACK! Popping operation cannot be performed."; }

        public EmptyStackException(String message) { this.msg = message; }

        public EmptyStackException(String message, Exception exception) : base(message, exception) { }

        public override string ToString()
        {
            return this.msg;
            //return base.ToString();
        }
    }
}
