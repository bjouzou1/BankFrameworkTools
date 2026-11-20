# BankFrameworkTools

Package framework **tiers** pour la migration/conversion webMethods.io.
Issu du split de `BankFrameworkJava` (volet utilitaires techniques).

## Contenu

| Namespace io | Services | Classe Java |
|---|---|---|
| `bank.framework.date` | `currentTimeMillis`, `elapsedMillis` | `bank.framework.tools.date` |
| `bank.framework.string` | `contains` | `bank.framework.tools.string` |

- Package Java : **`bank.framework.tools`** (`code/source/bank/framework/tools/`).
- Aucune dépendance (ni vers BankFrameworkLogging, ni vers un autre package).

## Origine

Split de `BankFrameworkJava` : renommage du namespace `bank.frmk.*` -> `bank.framework.*`
(harmonie de nommage). `BankFrameworkJava` est conservé pour l'instant (suppression
ultérieure).

## À FAIRE avant déploiement io

- **Recompiler les `.class` en Java 17** (bytecode class file version 61, cf. steering §4) :
  `javac --release 17` avec les jars webMethods (`wm-isserver.jar`...) au classpath.
  Les `.class` ne sont PAS fournis ici (pas de JDK 17 + libs wm sur la machine de split).
  Déposer le résultat sous `code/classes/bank/framework/tools/`.
- Déclaré comme **package tiers** (requires) dans les projets convertis qui invoquent
  `bank.framework.date` / `bank.framework.string`.
- Repo Git : à créer (cible GitHub cloud vs github.psa-cloud.com — tranchée séparément).
