import org.apache.thrift.TDeserializer                                          
import org.apache.thrift.TSerializer                                            
import org.apache.thrift.protocol.TCompactProtocol                              
                                                                                
                                                                                
                                                                                
val deserializer = new TDeserializer(new TCompactProtocol.Factory())
val serializer = new TSerializer(new TCompactProtocol.Factory())
