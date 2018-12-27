using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    public class ExitCommand : Command
    {
        public ExitCommand(String key, String description) : base(key, description) { }

        public override void Execute()
        {
            Environment.Exit(0);
        }
    }
}
