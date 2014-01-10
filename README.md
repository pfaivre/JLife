JLife
=====

### NOM
JLife - Simple simulateur du jeu de la vie de John Horton Conway.

### SYNOPSIS
	JLife [option]... [file]

### DESCRIPTION
Ce manuel décrit brièvement l'utilisation du proramme JLife.
JLife est un programme en ligne de commande simulant un espace en deux dimensions dans lequel sont appliquées les règles du jeu de la vie.
Le jeu de la vie est un automate cellulaire inventé par John Horton Conway en 1970.

### MODES
Par défaut, JLife fonctionne en mode automatique. Il peut aussi fonctionner en mode interactif ou mode silencieux.

JLife -a=50 Démarre le mode automatique avec un délai de 50 millisecondes.

JLife -i Démarre le mode interactif. L'utilisateur doit alors appuyer sur entrée pour faire défiler les générations.

JLife -g=500 -q Calcule immédiatement 500 générations et enregistre la grille résultante dans file.

À noter qu'une seule de ces trois options ne peut être appelée à la fois.

### OPTIONS
	-a, --auto[=delay]
Démarre le programme en mode automatique. delay est le temps entre chaque générations en millisecondes, par défaut 100.

	-g, --generations=max_gens
Spécifie le nombre maximal de générations à calculer. -1 pour pas de limite.

	-h, --help
Affiche un message d'aide et quitte le programme.

	-i, --interactive
Démarre le programme en mode interactif. L'utilisateur doit alors appuyer sur entrée pour passer à la génération suivante.

	-q, --quiet
Démarre le programme en mode rapide (sans délais) et n'affiche que la grille résultante.

### AUTEUR
Écrit par Pierre Faivre
