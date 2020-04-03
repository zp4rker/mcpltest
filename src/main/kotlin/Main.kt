import com.github.steveice10.mc.protocol.MinecraftProtocol
import com.github.steveice10.packetlib.Client
import com.github.steveice10.packetlib.event.session.*
import com.github.steveice10.packetlib.packet.Packet
import com.github.steveice10.packetlib.tcp.TcpSessionFactory

fun main(args: Array<String>) {
    val client = MinecraftProtocol(args[0], args[1])

    val connection = Client("mctest.zp4rker.com", 25565, client, TcpSessionFactory())
    connection.session.addListener(object: SessionListener {
        override fun connected(event: ConnectedEvent) {
            println("Successfully connected!")
        }

        override fun disconnecting(event: DisconnectingEvent) {
            println("Now disconnecting because ${event.reason}...")
        }

        override fun disconnected(event: DisconnectedEvent) {
            println("Disconnected because ${event.reason}!")
        }

        override fun packetReceived(event: PacketReceivedEvent) {
            println("Received packet of type: ${event.getPacket<Packet>().javaClass.name}")
        }

        override fun packetSending(event: PacketSendingEvent) {
            println("Sending packet of type: ${event.getPacket<Packet>().javaClass.name}")
        }

        override fun packetSent(event: PacketSentEvent) {
            println("Sent packet of type: ${event.getPacket<Packet>().javaClass.name}")
        }

    })

    connection.session.connect()
}