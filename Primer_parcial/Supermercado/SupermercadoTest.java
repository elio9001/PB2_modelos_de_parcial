
package //completar
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class SupermercadoTest {

    private Supermercado supermercado;
    private Producto leche;
    private Producto pan;
    private Producto chocolate;

    @Before
    public void setUp() {
        this.supermercado = new Supermercado("Supermercado Central");
        this.leche = new Producto(1, "Leche Entera", 1000.0);
        this.pan = new Producto(2, "Pan Lactal", 800.0);
        this.chocolate = new Producto(3, "Chocolate", 1200.0);
    }

    @Test
    public void queUnTicketPuedaTenerProductosRepetidosYCalcularSubtotal() {
        Ticket ticket = new TicketEfectivo("TICKET-0001");

        ticket.agregarProducto(leche);
        ticket.agregarProducto(leche); // Repetido
        ticket.agregarProducto(pan);

        assertEquals(Integer.valueOf(3), Integer.valueOf(ticket.getProductos().size()));
        assertEquals(2800.0, ticket.calcularSubtotal(), 0.01);
    }

    @Test
    public void queNoSeAgregueUnProductoNuloAlTicket() {
        Ticket ticket = new TicketEfectivo("TICKET-0001");

        Boolean resultado = ticket.agregarProducto(null);

        assertFalse(resultado);
        assertEquals(Integer.valueOf(0), Integer.valueOf(ticket.getProductos().size()));
    }

    @Test
    public void queSeCalculeCorrectamenteElTotalDeUnTicketEfectivoConDescuento() {
        Ticket ticket = new TicketEfectivo("TICKET-0001");
        ticket.agregarProducto(leche); // 1000.0

        assertEquals(1000.0, ticket.calcularSubtotal(), 0.01);
        assertEquals(900.0, ticket.calcularTotal(), 0.01);
    }

    @Test
    public void queSeCalculeCorrectamenteElTotalDeUnTicketFacturaConIva() {
        Ticket ticket = new TicketFactura("TICKET-0002");
        ticket.agregarProducto(leche); // 1000.0

        assertEquals(1000.0, ticket.calcularSubtotal(), 0.01);
        assertEquals(1210.0, ticket.calcularTotal(), 0.01);
    }

    @Test
    public void queSePuedaEncolarUnTicketEnElSupermercado() {
        Ticket ticket = new TicketEfectivo("TICKET-0001");
        ticket.agregarProducto(pan);

        Boolean resultado = supermercado.encolarTicket(ticket);

        assertTrue(resultado);
        assertEquals(Integer.valueOf(1), supermercado.getCantidadTicketsPendientes());
    }

    @Test
    public void queNoSePuedaEncolarUnTicketNulo() {
        Boolean resultado = supermercado.encolarTicket(null);

        assertFalse(resultado);
        assertEquals(Integer.valueOf(0), supermercado.getCantidadTicketsPendientes());
    }

    @Test
    public void queSePuedaCobrarElProximoTicketRespetandoOrdenFIFO() {
        Ticket ticket1 = new TicketEfectivo("TICKET-0001");
        Ticket ticket2 = new TicketFactura("TICKET-0002");

        supermercado.encolarTicket(ticket1);
        supermercado.encolarTicket(ticket2);

        Ticket ticketCobrado = supermercado.cobrarProximoTicket();

        assertNotNull(ticketCobrado);
        assertEquals("TICKET-0001", ticketCobrado.getCodigo());
        assertEquals(Integer.valueOf(1), supermercado.getCantidadTicketsPendientes());
        assertEquals(Integer.valueOf(1), supermercado.getCantidadTicketsCobrados());
    }

    @Test
    public void queAlCobrarSinTicketsEnColaDevuelvaNull() {
            Ticket ticketCobrado = supermercado.cobrarProximoTicket();

            assertNull(ticketCobrado);
            assertEquals(Integer.valueOf(0), supermercado.getCantidadTicketsCobrados());
    }

    @Test
    public void queSePuedaBuscarUnTicketCobradoPorCodigo() {
        //completar...

        //Ticket encontrado = supermercado.buscarTicketCobradoPorCodigo("TICKET-0001");

        //completar... 
    }

    @Test
    public void queAlProcesarTicketsSeActualiceElTotalRecaudadoYDisminuyaElPendiente() {
        //completar....
    }
}
