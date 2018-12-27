using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class ProgramState
    {
        private IStack<IStatement> exeStack;
        public IStack<IStatement> ExeStack
        {
            get
            {
                return this.exeStack;
            }
        }

        private IDictionary<String, int> symTable;
        public IDictionary<String, int> SymTable
        {
            get
            {
                return this.symTable;
            }
        }

        private IList<int> outTable;
        public IList<int> OutTable
        {
            get
            {
                return this.outTable;
            }
        }

        private IDictionary<int, MyTuple<String, StreamReader>> fileTable;
        public IDictionary<int, MyTuple<String, StreamReader>> FileTable
        {
            get
            {
                return this.fileTable;
            }
        }
        
        public ProgramState(IStack<IStatement> es, IDictionary<String, int> st, IList<int> ot, IDictionary<int, MyTuple<String, StreamReader>> ft, IStatement program)
        {
            this.exeStack = es;
            this.symTable = st;
            this.outTable = ot;
            this.fileTable = ft;

            this.exeStack.Push(program);
        }

        public int GetUniqueID()
        {
            int i = 1;
            foreach (int idx in this.fileTable.Keys())
            {
                if (idx == i)
                    i++;
            }
            return i;
        }

        public override string ToString()
        {
            String result = "";
            result = result + "EXECUTION STACK" + "\n" + result + "---------------" + "\n" + this.ExeStack.ToString() + "\n";
            result = result + "SYMBOL TABLE" + "\n" + "------------" + "\n" + this.SymTable.ToString() + "\n";
            result = result + "OUT" + "\n" + "---" + "\n" + this.OutTable.ToString() + "\n";
            result = result + "FILE TABLE" + "\n" + "----------" + "\n" + this.FileTable.ToString() + "\n";
            result = result + "\n";
            return result;
        }
    }
}
