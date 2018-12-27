using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    interface IStack<T>
    {
        void Push(T elem);
        T Pop(); 
        Boolean IsEmpty();
        String ToString();
    }
}
