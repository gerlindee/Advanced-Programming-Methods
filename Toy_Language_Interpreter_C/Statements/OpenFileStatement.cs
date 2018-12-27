using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class OpenFileStatement : IStatement
    {
        private String var_name;
        private String filename;

        public OpenFileStatement(String var, String name)
        {
            this.var_name = var;
            this.filename = name;
        }

        public ProgramState Execute(ProgramState state)
        {
            foreach(MyTuple<String, StreamReader> item in state.FileTable.Values())
            {
                if (item.First == this.var_name)
                    throw new ExistingFileException();
            }
            String fullPath = "D:\\facultate\\anul II\\metode avansate de programare\\laborator\\toy_language_interpreter_C#\\a7\\a7\\Text Files\\" + this.filename;
            StreamReader reader = new StreamReader(fullPath);
            MyTuple<String, StreamReader> tuple = new MyTuple<string, StreamReader>(filename, reader);
            int key = state.GetUniqueID();
            state.FileTable.Put(key, tuple);
            state.SymTable.Put(this.var_name, key);
            return state;
        }

        public override string ToString()
        {
            return "Open(" + this.var_name + "," + this.filename + ")";
        }
    }
}
