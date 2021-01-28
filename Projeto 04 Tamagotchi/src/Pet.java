public class Pet {
    private int energyMax, hungryMax, cleanMax;
    private int energy, hungry, clean;
    private int diamonds;
    private int age;
    private boolean alive;

    private void setEnergy(int value) {
        this.energy = value;
    }

    private void setHungry(int value) {
        this.hungry = value;
    }

    private void setClean(int value) {
        this.clean = value;
    }

    public int getEnergyMax() {
        return this.energyMax;
    }

    public void setEnergyMax(int energyMax) {
        this.energyMax = energyMax;
    }

    public int getHungryMax() {
        return this.hungryMax;
    }

    public void setHungryMax(int hungryMax) {
        this.hungryMax = hungryMax;
    }

    public int getCleanMax() {
        return this.cleanMax;
    }

    public void setCleanMax(int cleanMax) {
        this.cleanMax = cleanMax;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getHungry() {
        return this.hungry;
    }

    public int getClean() {
        return this.clean;
    }

    public int getDiamonds() {
        return this.diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void checkingAttributes() {
        // Valores maiores do que o maximo
        if (getEnergy() >= getEnergyMax()) {
            setEnergy(getEnergyMax());
        } else if (getHungry() >= getHungryMax()) {
            setHungry(getHungryMax());
        } else if (getClean() >= getCleanMax()) {
            setClean(getCleanMax());
        }

        // Valores menores que zero
        if (getEnergy() <= 0) {
            System.out.println("fail: pet morreu sem energia");
            setEnergy(0);
            setAlive(false);
        } else if (getHungry() <= 0) {
            System.out.println("fail: pet morreu de fome");
            setHungry(0);
            setAlive(false);
        } else if (getClean() <= 0) {
            System.out.println("fail: pet morreu de sujeira");
            setClean(0);
            setAlive(false);
        }
    }

    void deathMenssage() {
        System.out.println("fail: pet esta morto");
    }

    public void play() {
        if (!isAlive()) {
            deathMenssage();
        } else {
            setEnergy(getEnergy() - 2);
            setHungry(getHungry() - 1);
            setClean(getClean() - 3);
            setDiamonds(getDiamonds() + 1);
            setAge(getAge() + 1);
            checkingAttributes();
        }
    }

    public void shower() {
        if (!isAlive()) {
            deathMenssage();
        } else {
            setEnergy(getEnergy() - 3);
            setHungry(getHungry() - 1);
            setClean(getCleanMax());
            setDiamonds(getDiamonds() + 0);
            setAge(getAge() + 2);
            checkingAttributes();
        }
    }

    public void eat() {
        if (!isAlive()) {
            deathMenssage();
        } else {
            setEnergy(getEnergy() - 1);
            setHungry(getHungry() + 4);
            setClean(getClean() - 2);
            setDiamonds(getDiamonds() + 0);
            setAge(getAge() + 1);
            checkingAttributes();
        }
    }

    public void sleep() {
        if (!isAlive()) {
            deathMenssage();
        } else {
            int restoresSleep = getEnergyMax() - getEnergy();

            if (restoresSleep < 5) {
                System.out.println("fail: nao esta com sono");
            } else {
                setEnergy(getEnergy() + restoresSleep);
                setAge(getAge() + restoresSleep);
            }
        }
    }

    @Override
    public String toString() {
        return "E: " + energy + "/" + energyMax + ", S :" + hungry + "/" + hungryMax + ", L: " + clean + "/" + cleanMax
                + ", D: " + diamonds + ", I: " + age;
    }

    public Pet(int energyMax, int hungryMax, int cleanMax) {
        this.energyMax = energyMax;
        this.hungryMax = hungryMax;
        this.cleanMax = cleanMax;
        this.energy = energyMax;
        this.hungry = hungryMax;
        this.clean = cleanMax;
        this.alive = true;
        this.age = 0;
        this.diamonds = 0;
    }
}