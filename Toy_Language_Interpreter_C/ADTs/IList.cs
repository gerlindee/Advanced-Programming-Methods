using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    interface IList<T>
    {
        void AddFirst(T elem);
        bool IsEmpty();
        T Remove();
        String ToString();
    }
}
