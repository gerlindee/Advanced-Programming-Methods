using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class UndefinedVariableException : Exception
    {
        private String msg;

        public UndefinedVariableException() { this.msg = "UNDEFIEND VARIABLE! The variable has not been declared."; }

        public UndefinedVariableException(String message) { this.msg = "UNDEFINED VARIABLE! The variable " + message + " has not been declared."; }

        public UndefinedVariableException(String message, Exception exception) : base(message, exception) { }

        public override string ToString()
        {
            return this.msg;
            //return base.ToString();
        }

    }
}
