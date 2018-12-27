using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    public abstract class Command
    {
        private String key;
        public String Key
        {
            get
            {
                return this.key;
            }
        }

        private String description;
        public String Description
        {
            get
            {
                return this.description; 
            }
        }

        public Command(String k, String d)
        {
            this.key = k;
            this.description = d;
        }

        public abstract void Execute();
    }
}
