using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class ReadFileStatement : IStatement
    {
        private IExpression exp_file_id;
        private String var_name;

        public ReadFileStatement(IExpression exp, String name)
        {
            this.exp_file_id = exp;
            this.var_name = name;
        }

        public ProgramState Execute(ProgramState state)
        {
            try
            {
                int value = this.exp_file_id.Eval(state.SymTable);
                if (state.FileTable.Get(value).Second == default(StreamReader))
                    throw new MissingTextReaderException();
                String line = state.FileTable.Get(value).Second.ReadLine();
                Console.WriteLine(line);
                if (line == null)
                    throw new EndOfFileException();
                int new_value = int.Parse(line);
                state.SymTable.Put(this.var_name, new_value);
                return state;
            }
            catch (DivideByZeroException)
            {
                throw new DivideByZeroException("DIVISION BY ZERO. Cannot divide by 0.");
            }
            catch (UndefinedVariableException)
            {
                throw new UndefinedVariableException();
            }
        }

        public override string ToString()
        {
            return "read(" + this.exp_file_id.ToString() + "," + this.var_name + ")";
        }
    }
}
