using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    interface IStatement
    {
        String ToString();
        ProgramState Execute(ProgramState state);
    }
}
