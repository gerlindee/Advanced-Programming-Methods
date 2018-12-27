using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class EndOfFileException : Exception
    {
        private String msg;

        public EndOfFileException() { this.msg = "END OF STREAM! There are no values to be read from the stream."; }

        public EndOfFileException(String message) : base(message) { this.msg = message; }

        public EndOfFileException(String message, Exception exception) : base(message, exception) { }

        public override string ToString()
        {
            return this.msg;
            //return base.ToString();
        }

    }
}
