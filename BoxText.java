package image_crop;


//public class BoxText {
//	JComboBox comboBox = new JComboBox();
//	Object[] elements = new Object[] {"Cat", "Dog", "Lion", "Mouse"};
//	AutoCompleteSupport.install(comboBox, GlazedLists.eventListOf(elements));
//}

import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class BoxText {
  public static void main(String[] argv) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    List<String> list = new LinkedList<String>();
    
    list.add("Acidic Swamp Ooze");
    list.add("Ancestral Healing");
    list.add("Animal Companion");
    list.add("Arcane Explosion");
    list.add("Arcane Intellect");
    list.add("Arcane Missiles");
    list.add("Arcane Shot");
    list.add("Arcanite Reaper");
    list.add("Archmage");
    list.add("Assassin's Blade");
    list.add("Assassinate");
    list.add("Backstab");
    list.add("Blessing of Kings");
    list.add("Blessing of Might");
    list.add("Bloodfen Raptor");
    list.add("Bloodlust");
    list.add("Bluegill Warrior");
    list.add("Booty Bay Bodyguard");
    list.add("Boulderfist Ogre");
    list.add("Charge");
    list.add("Chillwind Yeti");
    list.add("Claw");
    list.add("Cleave");
    list.add("Consecration");
    list.add("Core Hound");
    list.add("Corruption");
    list.add("Dalaran Mage");
    list.add("Darkscale Healer");
    list.add("Deadly Poison");
    list.add("Divine Spirit");
    list.add("Dragonling Mechanic");
    list.add("Drain Life");
    list.add("Dread Infernal");
    list.add("Elven Archer");
    list.add("Execute");
    list.add("Fan of Knives");
    list.add("Fiery War Axe");
    list.add("Fire Elemental");
    list.add("Fireball");
    list.add("Flamestrike");
    list.add("Flametongue Totem");
    list.add("Frost Nova");
    list.add("Frost Shock");
    list.add("Frostbolt");
    list.add("Frostwolf Grunt");
    list.add("Frostwolf Warlord");
    list.add("Gnomish Inventor");
    list.add("Goldshire Footman");
    list.add("Grimscale Oracle");
    list.add("Guardian of Kings");
    list.add("Gurubashi Berserker");
    list.add("Hammer of Wrath");
    list.add("Hand of Protection");
    list.add("Healing Touch");
    list.add("Hellfire");
    list.add("Heroic Strike");
    list.add("Hex");
    list.add("Holy Light");
    list.add("Holy Nova");
    list.add("Holy Smite");
    list.add("Houndmaster");
    list.add("Humility");
    list.add("Hunter's Mark");
    list.add("Innervate");
    list.add("Ironbark Protector");
    list.add("Ironforge Rifleman");
    list.add("Ironfur Grizzly");
    list.add("Kill Command");
    list.add("Kobold Geomancer");
    list.add("Kor'kron Elite");
    list.add("Light's Justice");
    list.add("Lord of the Arena");
    list.add("Magma Rager");
    list.add("Mark of the Wild");
    list.add("Mind Blast");
    list.add("Mind Control");
    list.add("Mind Vision");
    list.add("Mirror Image");
    list.add("Moonfire");
    list.add("Mortal Coil");
    list.add("Multi-Shot");
    list.add("Murloc Raider");
    list.add("Murloc Tidehunter");
    list.add("Nightblade");
    list.add("Northshire Cleric");
    list.add("Novice Engineer");
    list.add("Oasis Snapjaw");
    list.add("Ogre Magi");
    list.add("Polymorph");
    list.add("Power Word: Shield");
    list.add("Raid Leader");
    list.add("Razorfen Hunter");
    list.add("Reckless Rocketeer");
    list.add("River Crocolisk");
    list.add("Rockbiter Weapon");
    list.add("Sacrificial Pact");
    list.add("Sap");
    list.add("Savage Roar");
    list.add("Sen'jin Shieldmasta");
    list.add("Shadow Bolt");
    list.add("Shadow Word: Death");
    list.add("Shadow Word: Pain");
    list.add("Shattered Sun Cleric");
    list.add("Shield Block");
    list.add("Shiv");
    list.add("Silverback Patriarch");
    list.add("Sinister Strike");
    list.add("Soulfire");
    list.add("Sprint");
    list.add("Starfire");
    list.add("Starving Buzzard");
    list.add("Stonetusk Boar");
    list.add("Stormpike Commando");
    list.add("Stormwind Champion");
    list.add("Stormwind Knight");
    list.add("Succubus");
    list.add("Swipe");
    list.add("Timber Wolf");
    list.add("Totemic Might");
    list.add("Tracking");
    list.add("Truesilver Champion");
    list.add("Tundra Rhino");
    list.add("Vanish");
    list.add("Voidwalker");
    list.add("Voodoo Doctor");
    list.add("War Golem");
    list.add("Warsong Commander");
    list.add("Water Elemental");
    list.add("Whirlwind");
    list.add("Wild Growth");
    list.add("Windfury");
    list.add("Windspeaker");
    list.add("Wolfrider");
    list.add("A Light in the Darkness");
    list.add("Aberrant Berserker");
    list.add("Abusive Sergeant");
    list.add("Abyssal Enforcer");
    list.add("Acherus Veteran");
    list.add("Acolyte of Agony");
    list.add("Acolyte of Pain");
    list.add("Adaptation");
    list.add("Air Elemental");
    list.add("Alleycat");
    list.add("Am'gam Rager");
    list.add("Amani Berserker");
    list.add("Ancient Brewmaster");
    list.add("Ancient of Blossoms");
    list.add("Animated Berserker");
    list.add("Arathi Weaponsmith");
    list.add("Arcane Anomaly");
    list.add("Arcane Artificer");
    list.add("Arcanologist");
    list.add("Arcanosmith");
    list.add("Argent Protector");
    list.add("Argent Squire");
    list.add("Backstreet Leper");
    list.add("Barkskin");
    list.add("Battle Rage");
    list.add("Bearshark");
    list.add("Beckoner of Evil");
    list.add("Benevolent Djinn");
    list.add("Betrayal");
    list.add("Big-Time Racketeer");
    list.add("Bilefin Tidehunter");
    list.add("Binding Heal");
    list.add("Bladed Cultist");
    list.add("Blastcrystal Potion");
    list.add("Blessing of Wisdom");
    list.add("Blood Imp");
    list.add("Bloodhoof Brave");
    list.add("Bloodsail Raider");
    list.add("Bloodworm");
    list.add("Blowgill Sniper");
    list.add("Bog Creeper");
    list.add("Boisterous Bard");
    list.add("Bone Baron");
    list.add("Bonemare");
    list.add("Breath of Sindragosa");
    list.add("Brrrloc");
    list.add("C'Thun's Chosen");
    list.add("Call in the Finishers");
    list.add("Candleshot");
    list.add("Carrion Grub");
    list.add("Cave Hydra");
    list.add("Cavern Shinyfinder");
    list.add("Cheat Death");
    list.add("Chillblade Champion");
    list.add("Circle of Healing");
    list.add("Cloaked Huntress");
    list.add("Cobalt Scalebane");
    list.add("Cold Blood");
    list.add("Coldwraith");
    list.add("Cone of Cold");
    list.add("Corrosive Sludge");
    list.add("Crackling Razormaw");
    list.add("Cruel Taskmaster");
    list.add("Crushing Hand");
    list.add("Cryomancer");
    list.add("Crypt Lord");
    list.add("Crystalweaver");
    list.add("Cult Apothecary");
    list.add("Cult Master");
    list.add("Cursed Disciple");
    list.add("Daring Reporter");
    list.add("Dark Arakkoa");
    list.add("Dark Conviction");
    list.add("Dark Iron Dwarf");
    list.add("Dark Pact");
    list.add("Darkshire Alchemist");
    list.add("Darkshire Councilman");
    list.add("Deadly Fork");
    list.add("Deadly Shot");
    list.add("Deadscale Knight");
    list.add("Deathspeaker");
    list.add("Defias Ringleader");
    list.add("Demonfire");
    list.add("Dire Mole");
    list.add("Dire Wolf Alpha");
    list.add("Divine Strength");
    list.add("Dragonslayer");
    list.add("Drain Soul");
    list.add("Drakkari Defender");
    list.add("Dread Corsair");
    list.add("Druid of the Claw");
    list.add("Drygulch Jailor");
    list.add("Drywhisker Armorer");
    list.add("Duskboar");
    list.add("Dust Devil");
    list.add("Earth Shock");
    list.add("Earthen Ring Farseer");
    list.add("Eggnapper");
    list.add("Elder Longneck");
    list.add("Eldritch Horror");
    list.add("Emerald Reaver");
    list.add("Enchanted Raven");
    list.add("Eviscerate");
    list.add("Evolved Kobold");
    list.add("Explosive Trap");
    list.add("Eye for an Eye");
    list.add("Faceless Behemoth");
    list.add("Faceless Summoner");
    list.add("Faerie Dragon");
    list.add("Fallen Sun Cleric");
    list.add("Fen Creeper");
    list.add("Feral Rage");
    list.add("Fiery Bat");
    list.add("Fire Fly");
    list.add("Fire Plume Phoenix");
    list.add("Firelands Portal");
    list.add("Flame Geyser");
    list.add("Flame Imp");
    list.add("Flamewreathed Faceless");
    list.add("Flanking Strike");
    list.add("Flesheating Ghoul");
    list.add("Fool's Bane");
    list.add("Forge of Souls");
    list.add("Forked Lightning");
    list.add("Freezing Potion");
    list.add("Freezing Trap");
    list.add("Friendly Bartender");
    list.add("Frost Elemental");
    list.add("Frozen Clone");
    list.add("Fungal Enchanter");
    list.add("Fungalmancer");
    list.add("Gadgetzan Socialite");
    list.add("Gemstudded Golem");
    list.add("Giant Mastodon");
    list.add("Giant Wasp");
    list.add("Gilded Gargoyle");
    list.add("Glacial Shard");
    list.add("Gnash");
    list.add("Grave Shambler");
    list.add("Green Jelly");
    list.add("Grievous Bite");
    list.add("Grim Necromancer");
    list.add("Grimestreet Outfitter");
    list.add("Grimestreet Smuggler");
    list.add("Grimscale Chum");
    list.add("Grimy Gadgeteer");
    list.add("Grook Fu Master");
    list.add("Grotesque Dragonhawk");
    list.add("Guild Recruiter");
    list.add("Hallucination");
    list.add("Harvest Golem");
    list.add("Healing Rain");
    list.add("Hired Gun");
    list.add("Hoarding Dragon");
    list.add("Hooded Acolyte");
    list.add("Hot Spring Guardian");
    list.add("Howlfiend");
    list.add("Hozen Healer");
    list.add("Hydrologist");
    list.add("Hyldnir Frostrider");
    list.add("I Know a Guy");
    list.add("Ice Barrier");
    list.add("Ice Fishing");
    list.add("Igneous Elemental");
    list.add("Infested Tauren");
    list.add("Inner Fire");
    list.add("Inner Rage");
    list.add("Iron Hide");
    list.add("Ironbeak Owl");
    list.add("Ironforge Portal");
    list.add("Ironwood Golem");
    list.add("Jade Behemoth");
    list.add("Jade Blossom");
    list.add("Jade Chieftain");
    list.add("Jade Lightning");
    list.add("Jade Shuriken");
    list.add("Jade Spirit");
    list.add("Jade Swarmer");
    list.add("Jeweled Macaw");
    list.add("Jungle Panther");
    list.add("Kabal Chemist");
    list.add("Kabal Lackey");
    list.add("Kabal Songstealer");
    list.add("Kabal Talonpriest");
    list.add("Kara Kazham!");
    list.add("Kindly Grandmother");
    list.add("Kobold Apprentice");
    list.add("Kobold Hermit");
    list.add("Kobold Librarian");
    list.add("Kooky Chemist");
    list.add("Lakkari Felhound");
    list.add("Leeching Poison");
    list.add("Leper Gnome");
    list.add("Lightning Bolt");
    list.add("Lightspawn");
    list.add("Loot Hoarder");
    list.add("Lost in the Jungle");
    list.add("Mad Bomber");
    list.add("Malchezaar's Imp");
    list.add("Mana Wyrm");
    list.add("Mark of Nature");
    list.add("Mark of Y'Shaarj");
    list.add("Mark of the Lotus");
    list.add("Medivh's Valet");
    list.add("Menagerie Magician");
    list.add("Menagerie Warden");
    list.add("Mirror Entity");
    list.add("Mistress of Mixtures");
    list.add("Mogu'shan Warden");
    list.add("N'Zoth's First Mate");
    list.add("Naga Corsair");
    list.add("Naturalize");
    list.add("Necrotic Geist");
    list.add("Nerubian Prophet");
    list.add("Nesting Roc");
    list.add("Netherspite Historian");
    list.add("Night Howler");
    list.add("Nightbane Templar");
    list.add("Noble Sacrifice");
    list.add("Oaken Summons");
    list.add("On the Hunt");
    list.add("Ornery Direhorn");
    list.add("Pantry Spider");
    list.add("Plated Beetle");
    list.add("Play Dead");
    list.add("Polluted Hoarder");
    list.add("Pompous Thespian");
    list.add("Possessed Villager");
    list.add("Potion of Heroism");
    list.add("Potion of Madness");
    list.add("Power Word: Tentacles");
    list.add("Power of the Wild");
    list.add("Priest of the Feast");
    list.add("Priestess of Elune");
    list.add("Primal Fusion");
    list.add("Primalfin Lookout");
    list.add("Psionic Probe");
    list.add("Psych-o-Tron");
    list.add("Pterrordax Hatchling");
    list.add("Public Defender");
    list.add("Purify");
    list.add("Radiant Elemental");
    list.add("Raging Worgen");
    list.add("Rampage");
    list.add("Ravaging Ghoul");
    list.add("Ravasaur Runt");
    list.add("Raven Familiar");
    list.add("Ravenous Pterrordax");
    list.add("Razorpetal Lasher");
    list.add("Razorpetal Volley");
    list.add("Red Mana Wyrm");
    list.add("Redemption");
    list.add("Repentance");
    list.add("Righteous Protector");
    list.add("Rockpool Hunter");
    list.add("Runic Egg");
    list.add("Sabretooth Stalker");
    list.add("Sanguine Reveler");
    list.add("Sated Threshadon");
    list.add("Scarlet Crusader");
    list.add("Scavenging Hyena");
    list.add("Sense Demons");
    list.add("Sewer Crawler");
    list.add("Shadow Ascendant");
    list.add("Shadow Rager");
    list.add("Shadow Strike");
    list.add("Shadowstep");
    list.add("Shaky Zipgunner");
    list.add("Shatter");
    list.add("Shieldbearer");
    list.add("Shifting Scroll");
    list.add("Shimmering Tempest");
    list.add("Shroom Brewer");
    list.add("Silence");
    list.add("Silver Hand Knight");
    list.add("Silver Vanguard");
    list.add("Silvermoon Guardian");
    list.add("Silvermoon Portal");
    list.add("Skelemancer");
    list.add("Slam");
    list.add("Sleepy Dragon");
    list.add("Smuggler's Crate");
    list.add("Smuggler's Run");
    list.add("Sneaky Devil");
    list.add("Snipe");
    list.add("Snowflipper Penguin");
    list.add("Sorcerer's Apprentice");
    list.add("Soul of the Forest");
    list.add("Southsea Deckhand");
    list.add("Southsea Squidface");
    list.add("Spawn of N'Zoth");
    list.add("Spellbreaker");
    list.add("Spellweaver");
    list.add("Spirit Claws");
    list.add("Spirit Lash");
    list.add("Spiteful Smith");
    list.add("Squirming Tentacle");
    list.add("Stand Against Darkness");
    list.add("Stegodon");
    list.add("Stitched Tracker");
    list.add("Stoneskin Basilisk");
    list.add("Stormcrack");
    list.add("Stormforged Axe");
    list.add("Stormwatcher");
    list.add("Stranglethorn Tiger");
    list.add("Street Trickster");
    list.add("Streetwise Investigator");
    list.add("Stubborn Gastropod");
    list.add("Sudden Betrayal");
    list.add("Summoning Portal");
    list.add("Sunborne Val'kyr");
    list.add("Swashburglar");
    list.add("Tainted Zealot");
    list.add("Tanaris Hogchopper");
    list.add("Tar Creeper");
    list.add("Tar Lord");
    list.add("Tar Lurker");
    list.add("Tauren Warrior");
    list.add("Temple Enforcer");
    list.add("Tentacle of N'Zoth");
    list.add("Thoughtsteal");
    list.add("Thrallmar Farseer");
    list.add("Thunder Lizard");
    list.add("Tidal Surge");
    list.add("Toothy Chest");
    list.add("Tortollan Forager");
    list.add("Tortollan Shellraiser");
    list.add("Toxic Sewer Ooze");
    list.add("Trogg Gloomeater");
    list.add("Tuskarr Fisherman");
    list.add("Twilight Elder");
    list.add("Twilight Flamecaller");
    list.add("Twilight Geomancer");
    list.add("Twisted Worgen");
    list.add("Ultrasaur");
    list.add("Unbound Elemental");
    list.add("Unidentified Elixir");
    list.add("Unidentified Shield");
    list.add("Unleash the Hounds");
    list.add("Usher of Souls");
    list.add("Venomancer");
    list.add("Venture Co. Mercenary");
    list.add("Verdant Longneck");
    list.add("Violet Illusionist");
    list.add("Violet Wurm");
    list.add("Volatile Elemental");
    list.add("Vryghoul");
    list.add("Vulgar Homunculus");
    list.add("Wax Elemental");
    list.add("Webweave");
    list.add("Wicked Skeleton");
    list.add("Wicked Witchdoctor");
    list.add("Windfury Harpy");
    list.add("Wisp");
    list.add("Worgen Greaser");
    list.add("Worgen Infiltrator");
    list.add("Wrath");
    list.add("Wretched Tiller");
    list.add("Young Dragonhawk");
    list.add("Youthful Brewmaster");
    list.add("Zealous Initiate");
    list.add("Zoobot");
    list.add("Abominable Bowman");
    list.add("Ancient Harbinger");
    list.add("Ancient of Lore");
    list.add("Ancient of War");
    list.add("Arcane Giant");
    list.add("Arcane Tyrant");
    list.add("Astral Tiger");
    list.add("Avenging Wrath");
    list.add("Bane of Doom");
    list.add("Bestial Wrath");
    list.add("Big Game Hunter");
    list.add("Biteweed");
    list.add("Bittertide Hydra");
    list.add("Blackguard");
    list.add("Blade of C'Thun");
    list.add("Bladed Gauntlet");
    list.add("Blazecaller");
    list.add("Blood Knight");
    list.add("Blood Warriors");
    list.add("Blood of The Ancient One");
    list.add("Bloodbloom");
    list.add("Blubber Baron");
    list.add("Branching Paths");
    list.add("Brass Knuckles");
    list.add("Brawl");
    list.add("Bright-Eyed Scout");
    list.add("Bring It On!");
    list.add("Burgly Bully");
    list.add("Cabal Shadow Priest");
    list.add("Cabalist's Tome");
    list.add("Call of the Wild");
    list.add("Call to Arms");
    list.add("Carnivorous Cube");
    list.add("Cataclysm");
    list.add("Charged Devilsaur");
    list.add("Chittering Tunneler");
    list.add("Corpsetaker");
    list.add("Corridor Creeper");
    list.add("Crazed Worshipper");
    list.add("Crushing Walls");
    list.add("Cryostasis");
    list.add("Curious Glimmerroot");
    list.add("Cyclopian Horror");
    list.add("DOOM!");
    list.add("Darkspeaker");
    list.add("Dead Man's Hand");
    list.add("Deathaxe Punisher");
    list.add("Deck of Wonders");
    list.add("Defias Cleaner");
    list.add("Dinomancy");
    list.add("Dinosize");
    list.add("Dirty Rat");
    list.add("Doomerang");
    list.add("Doomhammer");
    list.add("Doomsayer");
    list.add("Dragon's Fury");
    list.add("Dragonfire Potion");
    list.add("Dragonhatcher");
    list.add("Drakkari Enchanter");
    list.add("Earth Elemental");
    list.add("Embrace Darkness");
    list.add("Embrace the Shadow");
    list.add("Emerald Hive Queen");
    list.add("Eternal Sentinel");
    list.add("Evasion");
    list.add("Explore Un'Goro");
    list.add("Faceless Manipulator");
    list.add("Faceless Shambler");
    list.add("Fal'dorei Strider");
    list.add("Far Sight");
    list.add("Fatespinner");
    list.add("Fel Orc Soulfiend");
    list.add("Fight Promoter");
    list.add("Finders Keepers");
    list.add("Forbidden Ancient");
    list.add("Forbidden Flame");
    list.add("Forbidden Healing");
    list.add("Forbidden Shaping");
    list.add("Force of Nature");
    list.add("Furnacefire Colossus");
    list.add("Gentle Megasaur");
    list.add("Giant Anaconda");
    list.add("Giant Sand Worm");
    list.add("Glacial Mysteries");
    list.add("Gladiator's Longbow");
    list.add("Gluttonous Ooze");
    list.add("Gnomeferatu");
    list.add("Gorehowl");
    list.add("Grand Archivist");
    list.add("Greater Arcane Missiles");
    list.add("Hammer of Twilight");
    list.add("Hungry Crab");
    list.add("Ice Block");
    list.add("Kabal Trafficker");
    list.add("Kidnapper");
    list.add("Lay on Hands");
    list.add("Leatherclad Hogleader");
    list.add("Level Up!");
    list.add("Light's Sorrow");
    list.add("Living Mana");
    list.add("Lotus Assassin");
    list.add("Lotus Illusionist");
    list.add("Luckydo Buccaneer");
    list.add("Lunar Visions");
    list.add("Mana Geode");
    list.add("Manic Soulcaster");
    list.add("Meanstreet Marshal");
    list.add("Meat Wagon");
    list.add("Meteor");
    list.add("Mindgames");
    list.add("Molten Giant");
    list.add("Mountain Giant");
    list.add("Murloc Warleader");
    list.add("Nerubian Unraveler");
    list.add("Obsidian Statue");
    list.add("Patient Assassin");
    list.add("Pilfered Power");
    list.add("Piranha Launcher");
    list.add("Pit Lord");
    list.add("Preparation");
    list.add("Primalfin Champion");
    list.add("Primordial Drake");
    list.add("Primordial Glyph");
    list.add("Psychic Scream");
    list.add("Pyroblast");
    list.add("Rat Pack");
    list.add("Rattling Rascal");
    list.add("Reckless Flurry");
    list.add("Renounce Darkness");
    list.add("Rummaging Kobold");
    list.add("Scaled Nightmare");
    list.add("Sea Giant");
    list.add("Seeping Oozeling");
    list.add("Shadow Visions");
    list.add("Shadowcaster");
    list.add("Shadowform");
    list.add("Shield Slam");
    list.add("Shimmering Courser");
    list.add("Simulacrum");
    list.add("Skulking Geist");
    list.add("Sleep with the Fishes");
    list.add("Small-Time Recruits");
    list.add("Snake Trap");
    list.add("Snowfury Giant");
    list.add("Southsea Captain");
    list.add("Spectral Pillager");
    list.add("Spellbender");
    list.add("Spirit Echo");
    list.add("Spiteful Summoner");
    list.add("Stampede");
    list.add("Stone Sentinel");
    list.add("Sudden Genesis");
    list.add("Sword of Justice");
    list.add("Tentacles for Arms");
    list.add("To My Side!");
    list.add("Tomb Lurker");
    list.add("Tortollan Primalist");
    list.add("Toxic Arrow");
    list.add("Treachery");
    list.add("Twilight Acolyte");
    list.add("Twilight Summoner");
    list.add("Twisting Nether");
    list.add("Ultimate Infestation");
    list.add("Unlicensed Apothecary");
    list.add("Unstable Evolution");
    list.add("Validated Doomsayer");
    list.add("Vilefin Inquisitor");
    list.add("Vilespine Slayer");
    list.add("Void Ripper");
    list.add("Voidlord");
    list.add("Weasel Tunneler");
    list.add("Wind-up Burglebot");
    list.add("Windshear Stormcaller");
    list.add("Wisps of the Old Gods");
    list.add("Al'Akir the Windlord");
    list.add("Alexstrasza");
    list.add("Aluneth");
    list.add("Anomalus");
    list.add("Archbishop Benedictus");
    list.add("Archmage Antonidas");
    list.add("Arfus");
    list.add("Auctionmaster Beardo");
    list.add("Awaken the Makers");
    list.add("Aya Blackpaw");
    list.add("Barnes");
    list.add("Baron Geddon");
    list.add("Blood-Queen Lana'thel");
    list.add("Bloodmage Thalnos");
    list.add("Bolvar, Fireblood");
    list.add("C'Thun");
    list.add("Cairne Bloodhoof");
    list.add("Captain Greenskin");
    list.add("Cenarius");
    list.add("Cho'gall");
    list.add("Clutchmother Zavas");
    list.add("Deathwing");
    list.add("Deathwing, Dragonlord");
    list.add("Don Han'Cho");
    list.add("Dragon Soul");
    list.add("Dragoncaller Alanna");
    list.add("Edwin VanCleef");
    list.add("Elise the Trailblazer");
    list.add("Fandral Staghelm");
    list.add("Finja, the Flying Star");
    list.add("Fire Plume's Heart");
    list.add("Genzo, the Shark");
    list.add("Geosculptor Yip");
    list.add("Grommash Hellscream");
    list.add("Grumble, Worldshaker");
    list.add("Gruul");
    list.add("Hadronox");
    list.add("Hallazeal the Ascended");
    list.add("Harrison Jones");
    list.add("Hemet, Jungle Hunter");
    list.add("Herald Volazj");
    list.add("Hobart Grapplehammer");
    list.add("Hogger");
    list.add("Hogger, Doom of Elwynn");
    list.add("Illidan Stormrage");
    list.add("Inkmaster Solia");
    list.add("Ixlid, Fungal Lord");
    list.add("Jungle Giants");
    list.add("Kalimos, Primal Lord");
    list.add("Kathrena Winterwisp");
    list.add("Kazakus");
    list.add("King Krush");
    list.add("King Mosh");
    list.add("King Mukla");
    list.add("King Togwaggle");
    list.add("Kingsbane");
    list.add("Knuckles");
    list.add("Krul the Unshackled");
    list.add("Kun the Forgotten King");
    list.add("Lakkari Sacrifice");
    list.add("Leeroy Jenkins");
    list.add("Lilian Voss");
    list.add("Lord Jaraxxus");
    list.add("Lorewalker Cho");
    list.add("Lynessa Sunsorrow");
    list.add("Lyra the Sunshard");
    list.add("Madam Goya");
    list.add("Malfurion the Pestilent");
    list.add("Malkorok");
    list.add("Malygos");
    list.add("Marin the Fox");
    list.add("Master Oakheart");
    list.add("Mayor Noggenfogger");
    list.add("Medivh, the Guardian");
    list.add("Millhouse Manastorm");
    list.add("Moorabi");
    list.add("Moroes");
    list.add("Mukla, Tyrant of the Vale");
    list.add("N'Zoth, the Corruptor");
    list.add("Nat Pagle");
    list.add("Nat, the Darkfishe");
    list.add("Nozdormu");
    list.add("Onyxia");
    list.add("Open the Waygate");
    list.add("Ozruk");
    list.add("Patches the Pirate");
    list.add("Prince Keleseth");
    list.add("Prince Malchezaar");
    list.add("Prince Taldaram");
    list.add("Prince Valanar");
    list.add("Princess Huhuran");
    list.add("Professor Putricide");
    list.add("Prophet Velen");
    list.add("Pyros");
    list.add("Ragnaros, Lightlord");
    list.add("Raza the Chained");
    list.add("Rhok'delar");
    list.add("Rin, the First Disciple");
    list.add("Rotface");
    list.add("Sergeant Sally");
    list.add("Shaku, the Collector");
    list.add("Sherazin, Corpse Flower");
    list.add("Shifter Zerus");
    list.add("Sindragosa");
    list.add("Skull of the Man'ari");
    list.add("Soggoth the Slitherer");
    list.add("Sonya Shadowdancer");
    list.add("Spiritsinger Umbra");
    list.add("Sunkeeper Tarim");
    list.add("Swamp King Dred");
    list.add("Temporus");
    list.add("The Beast");
    list.add("The Black Knight");
    list.add("The Boogeymonster");
    list.add("The Caverns Below");
    list.add("The Curator");
    list.add("The Darkness");
    list.add("The Last Kaleidosaur");
    list.add("The Lich King");
    list.add("The Marsh Queen");
    list.add("The Runespear");
    list.add("The Voraxx");
    list.add("Tinkmaster Overspark");
    list.add("Tirion Fordring");
    list.add("Twig of the World Tree");
    list.add("Twin Emperor Vek'lor");
    list.add("Tyrantus");
    list.add("Unite the Murlocs");
    list.add("Val'anyr");
    list.add("White Eyes");
    list.add("Wickerflame Burnbristle");
    list.add("Woecleaver");
    list.add("Wrathion");
    list.add("Xaril, Poisoned Mind");
    list.add("Y'Shaarj, Rage Unbound");
    list.add("Yogg-Saron, Hope's End");
    list.add("Ysera");
    list.add("Zola the Gorgon");
    list.add("Abomination");
    list.add("Addled Grizzly");
    list.add("Alarm-o-Bot");
    list.add("Aldor Peacekeeper");
    list.add("Alley Armorsmith");
    list.add("Ancestral Spirit");
    list.add("Ancient Mage");
    list.add("Ancient Shieldbearer");
    list.add("Ancient Watcher");
    list.add("Angry Chicken");
    list.add("Arcane Golem");
    list.add("Argent Commander");
    list.add("Armorsmith");
    list.add("Arrogant Crusader");
    list.add("Auchenai Soulpriest");
    list.add("Avalanche");
    list.add("Avian Watcher");
    list.add("Babbling Book");
    list.add("Backroom Bouncer");
    list.add("Bite");
    list.add("Blackwater Pirate");
    list.add("Blade Flurry");
    list.add("Blessed Champion");
    list.add("Blizzard");
    list.add("Blood Razor");
    list.add("Blood To Ichor");
    list.add("Bloodfury Potion");
    list.add("Bloodsail Corsair");
    list.add("Bloodsail Cultist");
    list.add("Bomb Squad");
    list.add("Bone Drake");
    list.add("Book Wyrm");
    list.add("Cat Trick");
    list.add("Celestial Dreamer");
    list.add("Coldlight Oracle");
    list.add("Coldlight Seer");
    list.add("Commanding Shout");
    list.add("Cornered Sentry");
    list.add("Corpse Raiser");
    list.add("Corpse Widow");
    list.add("Corrupted Healbot");
    list.add("Corrupted Seer");
    list.add("Corrupting Mist");
    list.add("Counterfeit Coin");
    list.add("Counterspell");
    list.add("Crazed Alchemist");
    list.add("Cruel Dinomancer");
    list.add("Crystal Lion");
    list.add("Crystalline Oracle");
    list.add("Cult Sorcerer");
    list.add("Darkshire Librarian");
    list.add("Death Revenant");
    list.add("Defender of Argus");
    list.add("Defile");
    list.add("Demented Frostcaller");
    list.add("Demolisher");
    list.add("Desperate Stand");
    list.add("Despicable Dreadlord");
    list.add("Devilsaur Egg");
    list.add("Devolve");
    list.add("Devour Mind");
    list.add("Direhorn Hatchling");
    list.add("Disciple of C'Thun");
    list.add("Dispatch Kodo");
    list.add("Divine Favor");
    list.add("Doomcaller");
    list.add("Doomed Apprentice");
    list.add("Doomguard");
    list.add("Doppelgangster");
    list.add("Drakonid Operative");
    list.add("Druid of the Swarm");
    list.add("Duskbreaker");
    list.add("Eaglehorn Bow");
    list.add("Earthen Scales");
    list.add("Eater of Secrets");
    list.add("Ebon Dragonsmith");
    list.add("Elven Minstrel");
    list.add("Emperor Cobra");
    list.add("Envenom Weapon");
    list.add("Equality");
    list.add("Eternal Servitude");
    list.add("Ethereal Arcanist");
    list.add("Ethereal Peddler");
    list.add("Evolve");
    list.add("Evolving Spores");
    list.add("Exploding Bloatbat");
    list.add("Explosive Runes");
    list.add("Explosive Shot");
    list.add("Feeding Time");
    list.add("Felfire Potion");
    list.add("Felguard");
    list.add("Feral Gibberer");
    list.add("Feral Spirit");
    list.add("Fire Plume Harbinger");
    list.add("Flare");
    list.add("Forbidden Ritual");
    list.add("Forlorn Stalker");
    list.add("Free From Amber");
    list.add("Frothing Berserker");
    list.add("Frozen Crusher");
    list.add("Furbolg Mossbinder");
    list.add("Gadgetzan Auctioneer");
    list.add("Gadgetzan Ferryman");
    list.add("Gather Your Party");
    list.add("Getaway Kodo");
    list.add("Ghastly Conjurer");
    list.add("Golakka Crawler");
    list.add("Gravelsnout Knight");
    list.add("Greater Healing Potion");
    list.add("Greedy Sprite");
    list.add("Grimestreet Enforcer");
    list.add("Grimestreet Informant");
    list.add("Grimestreet Pawnbroker");
    list.add("Grimestreet Protector");
    list.add("Grizzled Guardian");
    list.add("Happy Ghoul");
    list.add("Headcrack");
    list.add("Hidden Cache");
    list.add("Holy Fire");
    list.add("Holy Wrath");
    list.add("Hooked Reaver");
    list.add("Howling Commander");
    list.add("Humongous Razorleaf");
    list.add("Hungry Ettin");
    list.add("Ice Breaker");
    list.add("Ice Walker");
    list.add("Imp Master");
    list.add("Infest");
    list.add("Infested Wolf");
    list.add("Injured Blademaster");
    list.add("Ivory Knight");
    list.add("Jade Claws");
    list.add("Jade Idol");
    list.add("Jinyu Waterspeaker");
    list.add("Journey Below");
    list.add("Kabal Courier");
    list.add("Kabal Crystal Runner");
    list.add("Keening Banshee");
    list.add("Keeper of the Grove");
    list.add("Kirin Tor Mage");
    list.add("Klaxxi Amber-Weaver");
    list.add("Knife Juggler");
    list.add("Kobold Barbarian");
    list.add("Kobold Illusionist");
    list.add("Kobold Monk");
    list.add("Lava Burst");
    list.add("Lesser Amethyst Spellstone");
    list.add("Lesser Diamond Spellstone");
    list.add("Lesser Emerald Spellstone");
    list.add("Lesser Jasper Spellstone");
    list.add("Lesser Mithril Spellstone");
    list.add("Lesser Onyx Spellstone");
    list.add("Lesser Pearl Spellstone");
    list.add("Lesser Ruby Spellstone");
    list.add("Lesser Sapphire Spellstone");
    list.add("Leyline Manipulator");
    list.add("Lightfused Stegodon");
    list.add("Lightning Storm");
    list.add("Lightwarden");
    list.add("Lightwell");
    list.add("Lone Champion");
    list.add("Lotus Agents");
    list.add("Maelstrom Portal");
    list.add("Mana Addict");
    list.add("Mana Bind");
    list.add("Mana Tide Totem");
    list.add("Mana Wraith");
    list.add("Mass Dispel");
    list.add("Master Swordsmith");
    list.add("Master of Disguise");
    list.add("Master of Evolution");
    list.add("Midnight Drake");
    list.add("Mimic Pod");
    list.add("Mind Control Tech");
    list.add("Mindbreaker");
    list.add("Mirage Caller");
    list.add("Mire Keeper");
    list.add("Misdirection");
    list.add("Moat Lurker");
    list.add("Molten Blade");
    list.add("Molten Reflection");
    list.add("Moonglade Portal");
    list.add("Mortal Strike");
    list.add("Mountainfire Armor");
    list.add("Murloc Tidecaller");
    list.add("Murmuring Elemental");
    list.add("Nourish");
    list.add("Obsidian Shard");
    list.add("Onyx Bishop");
    list.add("Perdition's Blade");
    list.add("Phantom Freebooter");
    list.add("Pint-Size Potion");
    list.add("Pint-Sized Summoner");
    list.add("Plague Scientist");
    list.add("Possessed Lackey");
    list.add("Potion of Polymorph");
    list.add("Primal Talismans");
    list.add("Primalfin Totem");
    list.add("Protect the King!");
    list.add("Questing Adventurer");
    list.add("Rallying Blade");
    list.add("Raptor Hatchling");
    list.add("Ravenholdt Assassin");
    list.add("Roll the Bones");
    list.add("Runeforge Haunter");
    list.add("SI:7 Agent");
    list.add("Saronite Chain Gang");
    list.add("Savagery");
    list.add("Savannah Highmane");
    list.add("Scorp-o-matic");
    list.add("Seadevil Stinger");
    list.add("Second-Rate Bruiser");
    list.add("Secretkeeper");
    list.add("Selfless Hero");
    list.add("Servant of Kalimos");
    list.add("Servant of Yogg-Saron");
    list.add("Shadow Essence");
    list.add("Shadow Madness");
    list.add("Shadow Sensei");
    list.add("Shadow Word: Horror");
    list.add("Shadowblade");
    list.add("Shadowflame");
    list.add("Shallow Gravedigger");
    list.add("Shellshifter");
    list.add("Shifting Shade");
    list.add("Shrieking Shroom");
    list.add("Silithid Swarmer");
    list.add("Silverware Golem");
    list.add("Siphon Soul");
    list.add("Skeram Cultist");
    list.add("Small-Time Buccaneer");
    list.add("Spiked Hogrider");
    list.add("Spikeridged Steed");
    list.add("Spreading Madness");
    list.add("Spreading Plague");
    list.add("Stampeding Kodo");
    list.add("Starfall");
    list.add("Steam Surger");
    list.add("Steward of Darkshire");
    list.add("Stolen Goods");
    list.add("Stonehill Defender");
    list.add("Strongshell Scavenger");
    list.add("Sunfury Protector");
    list.add("Sunwalker");
    list.add("Terrorscale Stalker");
    list.add("Thing from Below");
    list.add("Thistle Tea");
    list.add("Ticking Abomination");
    list.add("Tol'vir Stoneshaper");
    list.add("Tol'vir Warden");
    list.add("Trogg Beastrager");
    list.add("Twilight Darkmender");
    list.add("Twilight Drake");
    list.add("Twilight's Call");
    list.add("Undercity Huckster");
    list.add("Unidentified Maul");
    list.add("Unwilling Sacrifice");
    list.add("Upgrade!");
    list.add("Val'kyr Soulclaimer");
    list.add("Vaporize");
    list.add("Venomstrike Trap");
    list.add("Vicious Fledgling");
    list.add("Vinecleaver");
    list.add("Violet Teacher");
    list.add("Virmen Sensei");
    list.add("Void Terror");
    list.add("Volcanic Potion");
    list.add("Volcano");
    list.add("Volcanosaur");
    list.add("Voodoo Hexxer");
    list.add("Wandering Monster");
    list.add("Wild Pyromancer");
    list.add("Young Priestess");
    list.add("Amethyst Spellstone");
    list.add("Blessed Maul");
    list.add("Champion's Maul");
    list.add("Diamond Spellstone");
    list.add("Doppelgangster");
    list.add("Doppelgangster");
    list.add("Druid of the Swarm");
    list.add("Druid of the Swarm");
    list.add("Druid of the Swarm");
    list.add("Emerald Spellstone");
    list.add("Greater Amethyst Spellstone");
    list.add("Greater Diamond Spellstone");
    list.add("Greater Emerald Spellstone");
    list.add("Greater Jasper Spellstone");
    list.add("Greater Mithril Spellstone");
    list.add("Greater Onyx Spellstone");
    list.add("Greater Pearl Spellstone");
    list.add("Greater Ruby Spellstone");
    list.add("Greater Sapphire Spellstone");
    list.add("Hyena");
    list.add("Jasper Spellstone");
    list.add("Mithril Spellstone");
    list.add("Onyx Spellstone");
    list.add("Pearl Spellstone");
    list.add("Purifier's Maul");
    list.add("Ruby Spellstone");
    list.add("Sacred Maul");
    list.add("Sapphire Spellstone");
    list.add("Shellshifter");
    list.add("Shellshifter");
    list.add("Shellshifter");
    list.add("Spirit Wolf");
    list.add("Baine Bloodhoof");
    list.add("Finkle Einhorn");
    list.add("Grave Vengeance");
    list.add("Pyros");
    list.add("Pyros");
    list.add("The Darkness");
    list.add("The Storm Guardian");
    list.add("Twin Emperor Vek'nilash");
    list.add("Shadow of Nothing");
    list.add("Spellbender");
    list.add("The Ancient One");
    list.add("Bananas");
    list.add("Battle Axe");
    list.add("Blood Fury");
    list.add("Boar");
    list.add("Damaged Golem");
    list.add("Defender");
    list.add("Defias Bandit");
    list.add("Devilsaur");
    list.add("Druid of the Claw");
    list.add("Druid of the Claw");
    list.add("Druid of the Claw");
    list.add("Elixir of Hope");
    list.add("Elixir of Life");
    list.add("Elixir of Purity");
    list.add("Elixir of Shadows");
    list.add("Flame of Azzinoth");
    list.add("Frog");
    list.add("Gnoll");
    list.add("Heavy Axe");
    list.add("Hound");
    list.add("Huffer");
    list.add("Infernal");
    list.add("Leokk");
    list.add("Mechanical Dragonling");
    list.add("Mirror Image");
    list.add("Misha");
    list.add("Murloc Scout");
    list.add("Panther");
    list.add("Runed Shield");
    list.add("Rusty Hook");
    list.add("Serrated Shield");
    list.add("Sheep");
    list.add("Snake");
    list.add("Spiked Shield");
    list.add("Squire");
    list.add("Squirrel");
    list.add("Tower Shield +10");
    list.add("Treant");
    list.add("Treant");
    list.add("Violet Apprentice");
    list.add("Whelp");
    list.add("Worthless Imp");
    list.add("Healing Totem");
    list.add("Searing Totem");
    list.add("Silver Hand Recruit");
    list.add("Stoneclaw Totem");
    list.add("Wicked Knife");
    list.add("Wrath of Air Totem");

    Java2sAutoTextField textfield = new Java2sAutoTextField(list);
    textfield.setText("");
    frame.getContentPane().add(textfield);
    frame.pack();
    frame.setVisible(true);
  }
}

class Java2sAutoTextField extends JTextField {
  class AutoDocument extends PlainDocument {

    public void replace(int i, int j, String s, AttributeSet attributeset)
        throws BadLocationException {
      super.remove(i, j);
      insertString(i, s, attributeset);
    }

    public void insertString(int i, String s, AttributeSet attributeset)
        throws BadLocationException {
      if (s == null || "".equals(s))
        return;
      String s1 = getText(0, i);
      String s2 = getMatch(s1 + s);
      int j = (i + s.length()) - 1;
      if (isStrict && s2 == null) {
        s2 = getMatch(s1);
        j--;
      } else if (!isStrict && s2 == null) {
        super.insertString(i, s, attributeset);
        return;
      }
      if (autoComboBox != null && s2 != null)
        autoComboBox.setSelectedValue(s2);
      super.remove(0, getLength());
      super.insertString(0, s2, attributeset);
      setSelectionStart(j + 1);
      setSelectionEnd(getLength());
    }

    public void remove(int i, int j) throws BadLocationException {
      int k = getSelectionStart();
      if (k > 0)
        k--;
      String s = getMatch(getText(0, k));
      if (!isStrict && s == null) {
        super.remove(i, j);
      } else {
        super.remove(0, getLength());
        super.insertString(0, s, null);
      }
      if (autoComboBox != null && s != null)
        autoComboBox.setSelectedValue(s);
      try {
        setSelectionStart(k);
        setSelectionEnd(getLength());
      } catch (Exception exception) {
      }
    }

  }

  public Java2sAutoTextField(List list) {
    isCaseSensitive = false;
    isStrict = true;
    autoComboBox = null;
    if (list == null) {
      throw new IllegalArgumentException("values can not be null");
    } else {
      dataList = list;
      init();
      return;
    }
  }

  Java2sAutoTextField(List list, Java2sAutoComboBox b) {
    isCaseSensitive = false;
    isStrict = true;
    autoComboBox = null;
    if (list == null) {
      throw new IllegalArgumentException("values can not be null");
    } else {
      dataList = list;
      autoComboBox = b;
      init();
      return;
    }
  }

  private void init() {
    setDocument(new AutoDocument());
    if (isStrict && dataList.size() > 0)
      setText(dataList.get(0).toString());
  }

  private String getMatch(String s) {
    for (int i = 0; i < dataList.size(); i++) {
      String s1 = dataList.get(i).toString();
      if (s1 != null) {
        if (!isCaseSensitive && s1.toLowerCase().startsWith(s.toLowerCase()))
          return s1;
        if (isCaseSensitive && s1.startsWith(s))
          return s1;
      }
    }

    return null;
  }

  public void replaceSelection(String s) {
    AutoDocument _lb = (AutoDocument) getDocument();
    if (_lb != null)
      try {
        int i = Math.min(getCaret().getDot(), getCaret().getMark());
        int j = Math.max(getCaret().getDot(), getCaret().getMark());
        _lb.replace(i, j - i, s, null);
      } catch (Exception exception) {
      }
  }

  public boolean isCaseSensitive() {
    return isCaseSensitive;
  }

  public void setCaseSensitive(boolean flag) {
    isCaseSensitive = flag;
  }

  public boolean isStrict() {
    return isStrict;
  }

  public void setStrict(boolean flag) {
    isStrict = flag;
  }

  public List getDataList() {
    return dataList;
  }

  public void setDataList(List list) {
    if (list == null) {
      throw new IllegalArgumentException("values can not be null");
    } else {
      dataList = list;
      return;
    }
  }

  private List dataList;

  private boolean isCaseSensitive;

  private boolean isStrict;

  private Java2sAutoComboBox autoComboBox;
}

class Java2sAutoComboBox extends JComboBox {
  private class AutoTextFieldEditor extends BasicComboBoxEditor {

    private Java2sAutoTextField getAutoTextFieldEditor() {
      return (Java2sAutoTextField) editor;
    }

    AutoTextFieldEditor(java.util.List list) {
      editor = new Java2sAutoTextField(list, Java2sAutoComboBox.this);
    }
  }

  public Java2sAutoComboBox(java.util.List list) {
    isFired = false;
    autoTextFieldEditor = new AutoTextFieldEditor(list);
    setEditable(true);
    setModel(new DefaultComboBoxModel(list.toArray()) {

      protected void fireContentsChanged(Object obj, int i, int j) {
        if (!isFired)
          super.fireContentsChanged(obj, i, j);
      }

    });
    setEditor(autoTextFieldEditor);
  }

  public boolean isCaseSensitive() {
    return autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
  }

  public void setCaseSensitive(boolean flag) {
    autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
  }

  public boolean isStrict() {
    return autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
  }

  public void setStrict(boolean flag) {
    autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
  }

  public java.util.List getDataList() {
    return autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
  }

  public void setDataList(java.util.List list) {
    autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
    setModel(new DefaultComboBoxModel(list.toArray()));
  }

  void setSelectedValue(Object obj) {
    if (isFired) {
      return;
    } else {
      isFired = true;
      setSelectedItem(obj);
      fireItemStateChanged(new ItemEvent(this, 701, selectedItemReminder, 1));
      isFired = false;
      return;
    }
  }

  protected void fireActionEvent() {
    if (!isFired)
      super.fireActionEvent();
  }

  private AutoTextFieldEditor autoTextFieldEditor;

  private boolean isFired;

}
/* From http://java.sun.com/docs/books/tutorial/index.html */

/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * -Redistribution of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN") AND ITS
 * LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A
 * RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT
 * OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY,
 * ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed, licensed or intended for
 * use in the design, construction, operation or maintenance of any nuclear
 * facility.
 */