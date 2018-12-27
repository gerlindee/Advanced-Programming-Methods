using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class TextMenu
    {
        private Dictionary<String, Command> commands;

        public TextMenu()
        {
            this.commands = new Dictionary<string, Command>();
        }

        public void AddCommand(Command cmd)
        {
            this.commands.Add(cmd.Key, cmd);
        }

        private void PrintMenu()
        {
            foreach(Command cmd in this.commands.Values)
            {
                String line = String.Format("{0}: {1}", cmd.Key, cmd.Description);
                Console.WriteLine(line);
            }
            Console.WriteLine("\n");
        }

        public void Show()
        {
            while(true)
            {
                this.PrintMenu();
                String msg;
                Console.Write("Input the option: ");
                msg = Console.ReadLine();
                this.commands.TryGetValue(msg, out Command cmd);
                if(cmd == default(Command))
                {
                    Console.WriteLine("Invalid option.");
                    continue;
                }
                cmd.Execute();
            }

        }
    }
}
