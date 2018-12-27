using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class Repository : IRepo
    {
        private MyList<ProgramState> states;
        public MyList<ProgramState> States
        {
            get
            {
                return this.states;
            }
        }

        private String logFilePath;

        public Repository(String filePath)
        {
            this.states = new MyList<ProgramState>();
            this.logFilePath = filePath;
        }

        public ProgramState GetCrtPrg()
        {
            return this.states.Remove();
        }

        public bool IsEmpty()
        {
            return this.states.IsEmpty();
        }

        public void Add(ProgramState state)
        {
            this.states.AddFirst(state);
        }


        public void LogPrgStateExec(ProgramState state)
        {
            String fullPath = "D:\\facultate\\anul II\\metode avansate de programare\\laborator\\toy_language_interpreter_C#\\a7\\a7\\Text Files\\" + this.logFilePath;
            FileStream writeStream = new FileStream(fullPath, FileMode.Append);
            TextWriter writer = new StreamWriter(writeStream);
            writer.WriteLine(state.ToString());
            writer.Close();
        }
    }
}
