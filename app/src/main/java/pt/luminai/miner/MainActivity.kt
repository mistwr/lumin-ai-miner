package pt.luminai.miner

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*
import java.security.MessageDigest
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

class MainActivity : Activity() {
 private val running = AtomicBoolean(false)
 private var worker: Thread? = null
 private lateinit var status: TextView
 private lateinit var toggle: Button
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(40,64,40,40); setBackgroundColor(Color.rgb(13,13,13)); gravity = Gravity.CENTER_HORIZONTAL }
  fun label(s:String, size:Float):TextView = TextView(this).apply { text=s; textSize=size; setTextColor(Color.rgb(218,175,74)); gravity=Gravity.CENTER; setPadding(0,16,0,16) }
  layout.addView(label("LUMIN MINER AI",28f))
  layout.addView(label("LABORATÓRIO • SHA-256",16f))
  status=label("Pronto para benchmark local.\nNão está a minerar criptomoedas.",16f)
  layout.addView(status)
  toggle=Button(this).apply { text="INICIAR BENCHMARK"; setOnClickListener { if(running.get()) stop() else start() } }
  layout.addView(toggle)
  layout.addView(label("Sem carteira • Sem pool • Sem recompensas",12f))
  setContentView(layout)
 }
 private fun start() {
  if(!running.compareAndSet(false,true)) return
  toggle.text="PARAR BENCHMARK"
  worker=thread(name="lumin-benchmark") {
   val md=MessageDigest.getInstance("SHA-256")
   val input=ByteArray(80)
   var total=0L
   var interval=0L
   var last=System.nanoTime()
   while(running.get() && !Thread.currentThread().isInterrupted) {
    input[0]=(total and 255).toByte()
    input[1]=((total ushr 8) and 255).toByte()
    md.digest(input)
    total++; interval++
    val now=System.nanoTime()
    if(now-last >= 1_000_000_000L) {
     val rate=interval*1_000_000_000.0/(now-last)
     runOnUiThread { if(running.get()) status.text="Benchmark SHA-256: %.0f hashes/s\nNão é mineração real.".format(rate) }
     interval=0; last=now
    }
   }
  }
 }
 private fun stop() { running.set(false); worker?.interrupt(); toggle.text="INICIAR BENCHMARK"; status.text="Benchmark parado. Sem mineração real." }
 override fun onStop() { stop(); super.onStop() }
 override fun onDestroy() { stop(); super.onDestroy() }
}
