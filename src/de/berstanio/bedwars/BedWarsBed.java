package de.berstanio.bedwars;


import org.bukkit.Location;

public class BedWarsBed {

    private Location location;
    private BedWarsTeam bedWarsTeam;

    public BedWarsBed(Location location, BedWarsTeam bedWarsTeam) {
        setLocation(location);
        setBedWarsTeam(bedWarsTeam);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BedWarsTeam getBedWarsTeam() {
        return bedWarsTeam;
    }

    public void setBedWarsTeam(BedWarsTeam bedWarsTeam) {
        this.bedWarsTeam = bedWarsTeam;
    }
}
