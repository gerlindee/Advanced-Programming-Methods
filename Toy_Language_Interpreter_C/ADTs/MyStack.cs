using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class MyStack<T> : IStack<T>
    {
        private Stack<T> stack;
        public Stack<T> Stack
        {
            get
            {
                return this.stack;
            }
        }

        public MyStack()
        {
            this.stack = new Stack<T>();
        }

        public void Push(T elem)
        {
            this.stack.Push(elem);
        }

        public T Pop()
        {
            if (this.IsEmpty())
                throw new EmptyStackException();
            return this.stack.Pop(); 
        }

        public Boolean IsEmpty()
        {
            if (this.stack.ToArray().Length == 0)
                return true;
            return false;
        }

        public override string ToString()
        { 
            String result = "";
            T[] temp = this.stack.ToArray();
            foreach(T elem in temp)
            {
                result = result + elem.ToString() + "\n";
            }
            return result;
        }


    }
}
