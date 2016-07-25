package test;

import static org.junit.Assert.*;

import org.junit.Test;

import character.Inventory;
import items.Vace;
import rooms.GenericRoom;

public class VaceTest {

    @Test
    public void VaceInRoomTest() {
        GenericRoom genericRoom = new GenericRoom();
        Vace vace = new Vace("example vace", "example color");
        assertEquals(vace.getName(), "example vace");
        
        genericRoom.addItem(vace);
        assertTrue(genericRoom.getRoomInventory().hasItem(vace));
    }
    
    @Test
    public void breakVaceTest() {
        Vace vace = new Vace("example vace", "example color");
        assertFalse(vace.isBroken());
        vace.breakItem();
        assertTrue(vace.isBroken());
    }
    
    @Test
    public void takeVaceTest() {
        Inventory inventory = new Inventory();
        Inventory taker = new Inventory();
        Vace vace = new Vace("example vace", "example color");
        inventory.addItem(vace);
        assertTrue(inventory.hasItem(vace));
        assertFalse(taker.hasItem(vace));
        
        taker.takeItem(vace, inventory);
        assertTrue(taker.hasItem(vace));
        assertFalse(inventory.hasItem(vace));
    }

}
