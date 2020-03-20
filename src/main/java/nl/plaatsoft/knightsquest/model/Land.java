package nl.plaatsoft.knightsquest.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import nl.plaatsoft.knightsquest.tools.MyFactory;

/**
 * The Class Land.
 * 
 * @author wplaat
 */
public class Land {
	
	/** The gc. */
	private GraphicsContext gc;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The size. */
	private int size;
	
	/** The type. */
	private LandEnum type; 
	
	/** The scale. */
	private double scale;
	
	/** The polygon. */
	private Polygon polygon = new Polygon();
	
	/** The source. */
	// Needed to move player soldier
	private boolean source = false;
	
	/** The destination. */
	private boolean destination = false;

	/** The player. */
	private Player player;
	
	/** The region. */
	private int region;
	
	/** The soldier. */
	private Soldier soldier;
	
	/** The building. */
	private Building building;
	
	/**
	 * Instantiates a new land.
	 *
	 * @param gc the gc
	 * @param x the x
	 * @param y the y
	 * @param size the size
	 * @param type the type
	 */
	public Land(GraphicsContext gc, int x, int y, int size, LandEnum type) {
		this.gc = gc;
		this.x = x;
		this.y = y;
		this.type = type;
		this.size = size;
		
		int offset = 0;
		if ((y % 2)==1) {
			offset = size*2;
		}
		
		polygon.getPoints().addAll(new Double[] {
				(double)(x*(size*4)+offset), (double)size+(y*size),
				(double)size+(x*(size*4))+offset, (double)(y*size),
				(double)(size*2)+(x*(size*4))+offset, (double)(y*size),
				(double)(size*3)+(x*(size*4))+offset, (double)size+(y*size),
				(double)(size*2)+(x*(size*4))+offset, (double)(size*2)+(y*size),
				(double)size+(x*(size*4))+offset, (double)(size*2)+(y*size),				
				(double)(x*(size*4))+offset,(double)size+(y*size) });
	}
	
	/**
	 * Draw.
	 */
	public void draw() {
						
		//log.info("draw land ["+x+","+y+"]");
		
		int offset = 0;
		if ((y % 2)==1) {
			offset = size*2;
		} 
		
		gc.setGlobalAlpha(1);
		MyFactory.getLandDAO().getTexture(gc, type);

		gc.fillPolygon(
			new double[]{(x*(size*4))+offset, size+(x*(size*4))+offset,	(size*2)+(x*(size*4))+offset, (size*3)+(x*(size*4))+offset, 
					(size*2)+(x*(size*4))+offset, size+(x*(size*4))+offset,	0+(x*(size*4))+offset}, 
			new double[]{size+(y*size), (y*size), (y*size),	size+(y*size), (size*2)+(y*size), (size*2)+(y*size), size+(y*size)}, 7
			);
				
		if (player!=null) {
			gc.setGlobalAlpha(0.6);
			MyFactory.getPlayerDAO().getTexture(gc, player.getId());
			
			gc.fillPolygon(
				new double[]{(x*(size*4))+offset, size+(x*(size*4))+offset,	(size*2)+(x*(size*4))+offset, (size*3)+(x*(size*4))+offset, 
							(size*2)+(x*(size*4))+offset, size+(x*(size*4))+offset,	0+(x*(size*4))+offset}, 
				new double[]{size+(y*size), (y*size), (y*size),	size+(y*size), (size*2)+(y*size), (size*2)+(y*size), size+(y*size)}, 7);	
		}
		
		if (destination) {
			
			gc.setGlobalAlpha(0.6);
			gc.setFill(Color.ORANGE);
			
			gc.fillPolygon(
				new double[]{(x*(size*4))+offset, size+(x*(size*4))+offset,	(size*2)+(x*(size*4))+offset, (size*3)+(x*(size*4))+offset, 
							(size*2)+(x*(size*4))+offset, size+(x*(size*4))+offset,	0+(x*(size*4))+offset}, 
				new double[]{size+(y*size), (y*size), (y*size),	size+(y*size), (size*2)+(y*size), (size*2)+(y*size), size+(y*size)}, 7);	
		}
					
		gc.setGlobalAlpha(1);
		gc.setFill(Color.BLACK);		
		gc.strokePolyline(
				new double[]{(x*(size*4))+offset, size+(x*(size*4))+offset,	(size*2)+(x*(size*4))+offset, (size*3)+(x*(size*4))+offset, 
						(size*2)+(x*(size*4))+offset, size+(x*(size*4))+offset,	0+(x*(size*4))+offset}, 
				new double[]{size+(y*size), (y*size), (y*size),	size+(y*size), (size*2)+(y*size), (size*2)+(y*size), size+(y*size)}, 7
		);	
						
		if (size>=20) {
			gc.setFill(Color.WHITE);
			gc.fillText("["+x+","+y+"]",(x*(size*4))+offset+15,size+(y*size));
			gc.fillText(""+region,(x*(size*4))+offset+25,size+(y*size)+15);
		}
	
		if (building!=null) {			
			getBuilding().draw();
		}
		
		if (soldier!=null) {			
			getSoldier().draw();
		}
	}
		
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public LandEnum getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(LandEnum type) {
		this.type = type;
	}
	
	/**
	 * Gets the soldier.
	 *
	 * @return the soldier
	 */
	public Soldier getSoldier() {
		return soldier;
	}
	
	/**
	 * Sets the soldier.
	 *
	 * @param soldier the new soldier
	 */
	public void setSoldier(Soldier soldier) {
		this.soldier = soldier;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public int getRegion() {
		return region;
	}

	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(int region) {
		this.region = region;
	}
	
	/**
	 * Gets the polygon.
	 *
	 * @return the polygon
	 */
	public Polygon getPolygon() {
		return polygon;
	}

	/**
	 * Sets the polygon.
	 *
	 * @param polygon the new polygon
	 */
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
		
	/**
	 * Gets the gc.
	 *
	 * @return the gc
	 */
	public GraphicsContext getGc() {
		return gc;
	}

	/**
	 * Sets the gc.
	 *
	 * @param gc the new gc
	 */
	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Checks if is source.
	 *
	 * @return true, if is source
	 */
	public boolean isSource() {
		return source;
	}

	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(boolean source) {
		this.source = source;
	}

	/**
	 * Checks if is destination.
	 *
	 * @return true, if is destination
	 */
	public boolean isDestination() {
		return destination;
	}

	/**
	 * Sets the destination.
	 *
	 * @param destination the new destination
	 */
	public void setDestination(boolean destination) {
		this.destination = destination;
	}

	/**
	 * Gets the scale.
	 *
	 * @return the scale
	 */
	public double getScale() {
		return scale;
	}

	/**
	 * Sets the scale.
	 *
	 * @param scale the new scale
	 */
	public void setScale(double scale) {
		this.scale = scale;
		polygon.setScaleX(scale);
		polygon.setScaleY(scale);
	}

	/**
	 * Gets the building.
	 *
	 * @return the building
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * Sets the building.
	 *
	 * @param building the new building
	 */
	public void setBuilding(Building building) {
		this.building = building;
	}
}
