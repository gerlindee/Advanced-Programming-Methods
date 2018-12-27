using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    public class MyTuple<T1,T2>
    {
        private T1 first;
        private T2 second;

        public T1 First
        {
            get
            {
                return this.first;
            }

            set
            {
                this.first = value;
            }
        }

        public T2 Second
        {
            get
            {
                return this.second;
            }

            set
            {
                this.second = value;
            }
        }

        public MyTuple(T1 f, T2 s)
        {
            this.first = f;
            this.second = s;
        }

        public override string ToString()
        {
            return "(" + this.first.ToString() + "," + this.second.ToString() + ")";
        }
    }
}
