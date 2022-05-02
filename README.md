# ahk-mqtt-publcons
Ejemplo de publicador/consumidor MQTT


1) Correr Servidor

docker run --rm -it -p 1883:1883 eclipse-mosquitto:1.6.15

2)   Ejecutar al suscriptor/consumidor

```
# Solo funciona en Linux
# El canal del consumidor por defecto es “test”, está en el código
mvn clean compile exec:java -Dexec.mainClass="ahk.ConsumerAHK" -Dexec.args="cliente1"
```

3) Ejecutar al publicador
```
# Solo funciona en Linux
mvn clean compile exec:java -Dexec.mainClass="ahk.PublisherAHK"
```