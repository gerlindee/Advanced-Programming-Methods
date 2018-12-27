using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class MyDictionary<K,V> : IDictionary<K, V>
    {
        private Dictionary<K, V> dictionary;
        public Dictionary<K, V> Dictionary
        {
            get
            {
                return this.dictionary;
            }
        }

        public MyDictionary()
        {
            this.dictionary = new Dictionary<K, V>();
        }

        public void Put(K key, V value)
        {
            if (this.dictionary.TryGetValue(key, out V found_value) == true)
                this.Remove(key);
            this.dictionary.Add(key, value);
        }

        public V Get(K key)
        {
            V found_value;
            if (this.dictionary.TryGetValue(key, out found_value))
                return found_value;
            throw new UndefinedVariableException(key.ToString());
        }

        public void Remove(K key)
        {
            this.dictionary.Remove(key);
        }

        public List<K> Keys()
        {
            List<K> keyList = new List<K>(this.dictionary.Keys);
            return keyList;
        }

        public List<V> Values()
        {
            List<V> valueList = new List<V>(this.dictionary.Values);
            return valueList;
        }

        public override String ToString()
        {
            String result = "";
            foreach(K key in this.dictionary.Keys)
            {
                result = result + key.ToString() + " -> " + this.Get(key).ToString() + "\n";
            }
            return result;
        }
    
    }
}
