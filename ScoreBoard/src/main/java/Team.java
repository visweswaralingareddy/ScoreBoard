import java.util.Objects;

public class Team {
    private String name;
    private boolean isHome;

    public Team(String name, boolean isHome) {
        this.name = name;
        this.isHome = isHome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return isHome == team.isHome && name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isHome);
    }
}
