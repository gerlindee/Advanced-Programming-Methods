using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class MissingTextReaderException : Exception
    {
        private String msg;

        public MissingTextReaderException() { this.msg = "Cannot read from file. TextReader missing."; }

        public MissingTextReaderException(String message) : base(message) { this.msg = message; }

        public MissingTextReaderException(String message, Exception exception) : base(message, exception) { }

        public override string ToString()
        {
            return this.msg;
            //return base.ToString();
        }
    }
}
