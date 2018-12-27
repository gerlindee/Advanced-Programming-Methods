using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class RunExample : Command
    {
        private Controller controller;

        public RunExample(String key, String description, Controller ctrl) : base(key, description)
        {
            this.controller = ctrl;
        }

        public override void Execute()
        {
            int prg_num = 1;
            while(!this.controller.IsEmpty())
            {
                Console.Write("PROGRAM NUMBER: " + prg_num.ToString() + "\n");
                this.controller.AllStep();
                prg_num++;
            }
        }
    }
}
