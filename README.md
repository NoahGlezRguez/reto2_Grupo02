# reto2_Grupo02
Repositorio del grupo 02 para el reto 2 de DAM

Aquí va un chuletario ultra-rápido, pensado justo para clavar el título del commit y dejar los detalles para la descripción 👌

🧠 Fórmula mental
¿Qué hice? → type
¿Dónde lo hice? → scope
> type(scope): que hace el cambio

📌 TYPES (elige uno)
feat → nueva funcionalidad
fix → corrección de bug
refactor → cambio interno sin cambiar comportamiento
perf → mejora de rendimiento
style → formato / lint / espacios / ; (no lógica)
test → tests nuevos o corregidos
docs → documentación
build → dependencias, build, versiones
ops → infra, CI/CD, deploy
chore → tareas generales (init, configs, cleanup)

📦 SCOPES (elige uno simple)

Usa una palabra clara. Ejemplos comunes:

Funcionalidad / dominio
auth
user
payment
orders
search
Capa técnica
api
ui
frontend
backend
db

Tooling / proyecto
config
deps
ci
docker
build

👉 Si no aporta valor, omite el scope.

✍️ DESCRIPCIÓN (reglas rápidas)

verbo en presente e imperativo
corta y clara
sin mayúscula inicial
sin punto final

✅ Ejemplos buenos
feat(auth): add password reset
fix(api): handle empty response
refactor(user): simplify validation logic
docs(readme): update setup steps
chore(ci): adjust pipeline cache

💥 Breaking change

Si rompe algo:

feat(api)!: remove status endpoint

🧩 En una frase

Título = resumen para humanos
Descripción = detalles para el futuro tú




------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Organización grupal:
# 🧠 ¿Qué es un Pull Request (PR)?

Un Pull Request es básicamente decir:

“Oye equipo, he terminado algo en mi rama.
¿Podemos revisarlo y meterlo en main?”

No mezcla código automáticamente.
👉 Pide permiso + revisión.

# 🧩 Traducción a lenguaje humano

Imagina esto:

main = versión oficial del proyecto

tu-rama = tu mesa de trabajo

Pull Request = llevar tu trabajo a la mesa grande para que los demás lo miren

# 📍 ¿Cuándo se usa un Pull Request?

Siempre que quieras:

pasar código de tu rama → main

compartir cambios

evitar romper el proyecto

⚠️ Nunca se hace merge directo sin PR (aunque seáis 4 amigos).

# 🔁 Flujo completo de un Pull Request (PASO A PASO)
1️⃣ Trabajas en tu rama
git checkout rama-ana
# haces cambios
git add .
git commit -m "Añade estructura inicial de la web"
git push origin rama-ana


Hasta aquí, solo tu rama.

2️⃣ Vas a GitHub

GitHub suele mostrar un botón grande tipo:

“Compare & pull request”

Si no:

Repo → Pull requests → New pull request

3️⃣ Configuras el Pull Request

Seleccionas:

Base: main ← (aquí va el código)

Compare: rama-ana ← (tu trabajo)

✍️ Título claro:

Estructura inicial web

📝 Descripción simple:

Qué has hecho

Si falta algo

Si rompe algo

Ejemplo:

- Añadida estructura HTML básica
- CSS inicial
- No afecta a Java ni a la BD

4️⃣ Revisión (parte más importante)

Antes de hacer merge:

Otro compañero entra al PR

Mira los cambios

Puede:

aprobar ✅

comentar

pedir cambios

💡 Esto no es criticar, es evitar errores.

5️⃣ Merge 🎉

Cuando está OK:

Botón “Merge pull request”

GitHub une tu rama con main

👉 Tu código ya es oficial.

# 🧯 ¿Qué pasa con tu rama después?

Sigues trabajando con ella y repites el proceso con nuevos cambios.

# ⚠️ Errores típicos de principiantes

Evítalos y ya irás muy bien:

❌ Hacer PR gigantes (“todo el proyecto”)
❌ No explicar qué hace el PR
❌ Trabajar semanas sin actualizar tu rama
❌ Hacer merge sin que nadie lo mire

# 🧪 ¿Qué pasa si hay conflictos?

GitHub te avisa:

“This branch has conflicts”

No es el fin del mundo:

Significa que alguien tocó lo mismo

Se resuelve eligiendo qué versión queda

Mejor hacerlo entre los dos implicados

# 🧠 Resumen ultra corto

Pull Request = petición para meter tu código en main

Siempre desde tu rama

Sirve para revisar, hablar y evitar errores

Es una red de seguridad
