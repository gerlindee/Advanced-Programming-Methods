using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class CloseFileStatement : IStatement
    {
        private IExpression exp_file_id;

        public CloseFileStatement(IExpression id)
        {
            this.exp_file_id = id;
        }

        public ProgramState Execute(ProgramState state)
        {
            try
            {
                int value = this.exp_file_id.Eval(state.SymTable);
                StreamReader reader = state.FileTable.Get(value).Second;
                if (reader == default(StreamReader))
                    throw new UndefinedVariableException(this.exp_file_id.ToString());
                reader.Close();
                state.FileTable.Remove(value);
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
            catch (UndefinedOperationException)
            {
                throw new UndefinedOperationException();
            }
        }

        public override string ToString()
        {
            return "close(" + this.exp_file_id.ToString() + ")";
        }
    }
}
