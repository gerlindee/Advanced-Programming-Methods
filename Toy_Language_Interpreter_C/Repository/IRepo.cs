using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    interface IRepo
    {
        ProgramState GetCrtPrg();
        bool IsEmpty();
        void Add(ProgramState state);
        void LogPrgStateExec(ProgramState state);
    }
}
