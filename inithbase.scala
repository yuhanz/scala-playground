import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Get
import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.client.HTable
import org.apache.hadoop.hbase.KeyValue
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.client.ResultScanner
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.util.Bytes
import org.apache.thrift.TDeserializer
import org.apache.thrift.TSerializer
import org.apache.thrift.protocol.TCompactProtocol



val deserializer = new TDeserializer(new TCompactProtocol.Factory()) 
val conf = HBaseConfiguration.create()
conf.addResource("hbase-site.xml")

val admin = new HBaseAdmin(conf)

//println( admin.listTables() )

def getFromHbase(tableName: String, row : String, cf : String, qualifier : String): Array[Byte] = {
    val table = new HTable(conf, tableName);
    val result = table.get(new Get(row.getBytes).addColumn(cf.getBytes, qualifier.getBytes))
    val value = result.getValue(cf.getBytes, qualifier.getBytes)
    return value                                                               
}

def getRawFromHbase(tableName: String, row : String, cf : String, qualifier : String): Array[KeyValue] = {
    val table = new HTable(conf, tableName);                                    
    val result = table.get(new Get(row.getBytes).addColumn(cf.getBytes, qualifier.getBytes))
    val value = result.raw
    return value                
}  


def scanHbase(tableName: String, cf: String, qualifier: String) :Array[Array[Byte]] = {
    val table =  new HTable(conf, tableName);
    val result = table.getScanner(cf.getBytes, qualifier.getBytes)
    val it = result.iterator
    var array = Array[Array[Byte]]()
    while(it.hasNext) {
        it.next
        array +:= it.next.getValue(cf.getBytes, qualifier.getBytes).asInstanceOf[Array[Byte]]
    }

    return array
}
