using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class UndefinedOperationException : Exception
    {
        private String msg;

        public UndefinedOperationException() { }

        public UndefinedOperationException(String message) : base(message) { this.msg = message; }

        public UndefinedOperationException(String message, Exception exception) : base(message, exception) { this.msg = message; }

        public override string ToString()
        {
            return this.msg;
            // return base.ToString();
        }
    }
}
