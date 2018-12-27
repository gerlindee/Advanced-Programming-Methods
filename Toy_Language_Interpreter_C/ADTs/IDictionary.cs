using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    public interface IDictionary<K,V>
    {
        void Put(K key, V value);
        V Get(K key);
        List<K> Keys();
        List<V> Values();
        void Remove(K key);
        String ToString();
    }
}
