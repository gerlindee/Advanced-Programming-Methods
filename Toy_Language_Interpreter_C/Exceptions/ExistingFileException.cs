using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class ExistingFileException : Exception
    {
        private String msg;

        public ExistingFileException() { this.msg = "File given file already exists."; }

        public ExistingFileException(String message) : base(message) { this.msg = "File given file already exists."; }

        public ExistingFileException(String message, Exception exception) : base(message, exception) { }

        public override string ToString()
        {
            return this.msg;
        }
    }
}
