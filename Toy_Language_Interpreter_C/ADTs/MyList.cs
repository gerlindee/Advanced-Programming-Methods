using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class MyList<T> : IList<T>
    {
        private LinkedList<T> list;
        public LinkedList<T> List
        {
            get
            {
                return this.list;
            }
        }

        public MyList()
        {
            this.list = new LinkedList<T>();
        }

        public void AddFirst(T elem)
        {
            this.list.AddFirst(elem);
        }

        public T Remove()
        {
            T node = this.list.ElementAt(0);
            this.list.RemoveLast();
            return node;
        }

        public bool IsEmpty()
        {
            if (this.list.ToArray().Length == 0)
                return true;
            return false;
        }

        public override String ToString()
        {
            String result = "";
            T[] temp = this.list.ToArray();
            foreach(T elem in temp)
            {
                result = result + elem.ToString() + "\n";
            }
            return result;
        }
    }
}
